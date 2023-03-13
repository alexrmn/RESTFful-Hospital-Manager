package ro.alexrmn.hospitalmanagerbackend.service;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.alexrmn.hospitalmanagerbackend.model.Appointment;
import ro.alexrmn.hospitalmanagerbackend.model.Doctor;
import ro.alexrmn.hospitalmanagerbackend.model.Patient;
import ro.alexrmn.hospitalmanagerbackend.model.ETimeSlot;
import ro.alexrmn.hospitalmanagerbackend.repository.AppointmentRepository;
import ro.alexrmn.hospitalmanagerbackend.repository.DoctorRepository;
import ro.alexrmn.hospitalmanagerbackend.repository.PatientRepository;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    public Appointment createAppointment() {
        Doctor doctor = doctorRepository.findById(52L)
                .orElseThrow(() -> new EntityNotFoundException("Doctor not found"));
        Patient patient = patientRepository.findById(2L)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found"));
        ETimeSlot ETimeSlot = ETimeSlot.SLOT_9_10;
        LocalDate date = LocalDate.now();
        Appointment appointment = Appointment.builder()
                .date(date)
                .ETimeSlot(ETimeSlot)
                .doctor(doctor)
                .patient(patient)
                .build();
        if (appointmentRepository.existsByDateAndTimeSlotAndDoctor(date, ETimeSlot, doctor)) {
            throw new EntityExistsException("Timeslot is not free.");
        } else {
           return appointmentRepository.save(appointment);
        }

    }
}
