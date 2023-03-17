package ro.alexrmn.hospitalmanagerbackend.service;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ro.alexrmn.hospitalmanagerbackend.exception.NotAuthorizedException;
import ro.alexrmn.hospitalmanagerbackend.model.*;
import ro.alexrmn.hospitalmanagerbackend.model.dto.AppointmentDto;
import ro.alexrmn.hospitalmanagerbackend.model.dto.CreateAppointmentDto;
import ro.alexrmn.hospitalmanagerbackend.repository.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final TimeSlotRepository timeSlotRepository;
    private final SpecialtyRepository specialtyRepository;

    public AppointmentDto createAppointment(CreateAppointmentDto createAppointmentDto) {
        Doctor doctor = doctorRepository.findById(createAppointmentDto.getDoctorId())
                .orElseThrow(() -> new EntityNotFoundException("Doctor not found"));
        Patient patient = patientRepository.findById(createAppointmentDto.getPatientId())
                .orElseThrow(() -> new EntityNotFoundException("Patient not found"));
        Specialty specialty = specialtyRepository.findById(createAppointmentDto.getSpecialtyId())
                .orElseThrow(() -> new EntityNotFoundException("Specialty not found"));
        TimeSlot timeSlot = timeSlotRepository.findById(createAppointmentDto.getTimeslotId())
                .orElseThrow(() -> new EntityNotFoundException("Timeslot not found"));

        LocalDate date = createAppointmentDto.getDate();
        Appointment appointment = Appointment.builder()
                .date(date)
                .timeSlot(timeSlot)
                .doctor(doctor)
                .patient(patient)
                .specialty(specialty)
                .build();
        if (appointmentRepository.existsByDateAndTimeSlotAndDoctor(date, timeSlot, doctor)) {
            throw new EntityExistsException("Timeslot is not free.");
        } else {
           return appointmentRepository.save(appointment).toDto();
        }

    }

    @Override
    public List<AppointmentDto> getUpcomingAppointments(Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found"));
        List<Appointment> appointments = appointmentRepository.findByPatient(patient);
        List<Appointment> filteredAppointments = new ArrayList<>();

        //checking if appointments are upcoming
        LocalDateTime now = LocalDateTime.now();
        for (Appointment appointment : appointments) {
            LocalDateTime appDateTime = getAppointmentDateTime(appointment);
            if (appDateTime.isAfter(now)) {
                filteredAppointments.add(appointment);
            }
        }
        return filteredAppointments.stream().map(Appointment::toDto).toList();

    }

    @Override
    public List<AppointmentDto> getAppointmentHistory(Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found"));
        List<Appointment> appointments = appointmentRepository.findByPatient(patient);
        List<Appointment> filteredAppointments = new ArrayList<>();

        //checking if appointments already happened
        for (Appointment appointment : appointments) {
            if (getAppointmentDateTime(appointment).isBefore(LocalDateTime.now())) {
                filteredAppointments.add(appointment);
            }
        }
        return filteredAppointments.stream().map(Appointment::toDto).toList();
    }


    @Override
    public void deleteAppointment(Long appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new EntityNotFoundException("Appointment not found"));
        if (!appointmentBelongsToUser(appointment)) {
            throw new NotAuthorizedException("User is not authorized to delete this appointment");
        }
        if (getAppointmentDateTime(appointment).isBefore(LocalDateTime.now())) {
            throw new NotAuthorizedException("Can't delete an appointment that already took place");
        }
        appointmentRepository.delete(appointment);
    }

    @Override
    public AppointmentDto updateAppointment(Long appointmentId, CreateAppointmentDto createAppointmentDto) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new EntityNotFoundException("Appointment not found"));
        Doctor doctor = doctorRepository.findById(createAppointmentDto.getDoctorId())
                .orElseThrow(() -> new EntityNotFoundException("Doctor not found"));
        Specialty specialty = specialtyRepository.findById(createAppointmentDto.getSpecialtyId())
                .orElseThrow(() -> new EntityNotFoundException("Specialty not found"));
        TimeSlot timeSlot = timeSlotRepository.findById(createAppointmentDto.getTimeslotId())
                .orElseThrow(() -> new EntityNotFoundException("Timeslot not found"));
        appointment.setDate(createAppointmentDto.getDate());
        appointment.setDoctor(doctor);
        appointment.setSpecialty(specialty);
        appointment.setTimeSlot(timeSlot);
        appointmentRepository.save(appointment);
        return appointment.toDto();
    }

    @Override
    public List<AppointmentDto> getAppointmentsByDoctor(Long doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new EntityNotFoundException("Doctor not found"));
        return appointmentRepository.findByDoctor(doctor).stream()
                .map(Appointment::toDto)
                .toList();

    }

    @Override
    public AppointmentDto getAppointment(Long appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new EntityNotFoundException("Appointment not found"));
        return appointment.toDto();
    }

    private boolean appointmentBelongsToUser(Appointment appointment) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return Objects.equals(appointment.getPatient().getId(), user.getId());
    }

    private static LocalDateTime getAppointmentDateTime(Appointment appointment) {
        return appointment.getDate().atTime(appointment.getTimeSlot().getValue().getTime());
    }


}
