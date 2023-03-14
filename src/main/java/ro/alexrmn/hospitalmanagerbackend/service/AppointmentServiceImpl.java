package ro.alexrmn.hospitalmanagerbackend.service;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.alexrmn.hospitalmanagerbackend.model.*;
import ro.alexrmn.hospitalmanagerbackend.model.dto.CreateAppointmentDto;
import ro.alexrmn.hospitalmanagerbackend.repository.*;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final TimeSlotRepository timeSlotRepository;
    private final SpecialtyRepository specialtyRepository;

    public Appointment createAppointment(CreateAppointmentDto createAppointmentDto) {
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
           return appointmentRepository.save(appointment);
        }

    }
}
