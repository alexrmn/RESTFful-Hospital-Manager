package ro.alexrmn.hospitalmanagerbackend.service;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.alexrmn.hospitalmanagerbackend.model.*;
import ro.alexrmn.hospitalmanagerbackend.repository.AppointmentRepository;
import ro.alexrmn.hospitalmanagerbackend.repository.DoctorRepository;
import ro.alexrmn.hospitalmanagerbackend.repository.PatientRepository;
import ro.alexrmn.hospitalmanagerbackend.repository.TimeSlotRepository;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final TimeSlotRepository timeSlotRepository;

    public Appointment createAppointment() {
        Doctor doctor = doctorRepository.findById(1L)
                .orElseThrow(() -> new EntityNotFoundException("Doctor not found"));
        Patient patient = patientRepository.findById(2L)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found"));

        ETimeSlot eTimeSlot = ETimeSlot.SLOT_9_10;
        TimeSlot timeSlot = timeSlotRepository.findByValue(eTimeSlot)
                .orElseThrow(() -> new EntityNotFoundException("Timeslot not found"));

        LocalDate date = LocalDate.now();
        Appointment appointment = Appointment.builder()
                .date(date)
                .timeSlot(timeSlot)
                .doctor(doctor)
                .patient(patient)
                .build();
        if (appointmentRepository.existsByDateAndTimeSlotAndDoctor(date, timeSlot, doctor)) {
            throw new EntityExistsException("Timeslot is not free.");
        } else {
           return appointmentRepository.save(appointment);
        }

    }
}
