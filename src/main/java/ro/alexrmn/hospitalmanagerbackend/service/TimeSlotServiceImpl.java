package ro.alexrmn.hospitalmanagerbackend.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.alexrmn.hospitalmanagerbackend.model.Appointment;
import ro.alexrmn.hospitalmanagerbackend.model.Doctor;
import ro.alexrmn.hospitalmanagerbackend.model.TimeSlot;
import ro.alexrmn.hospitalmanagerbackend.repository.AppointmentRepository;
import ro.alexrmn.hospitalmanagerbackend.repository.DoctorRepository;
import ro.alexrmn.hospitalmanagerbackend.repository.TimeSlotRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TimeSlotServiceImpl implements TimeSlotService {

    private final TimeSlotRepository timeSlotRepository;
    private final AppointmentRepository appointmentRepository;
    private  final DoctorRepository doctorRepository;

    @Override
    public List<TimeSlot> getTimeSlots() {
        return timeSlotRepository.findAll();
    }

    @Override
    public List<TimeSlot> getFreeTimeSlotsByDateAndDoctor(LocalDate date, Long doctorId) {
       List<TimeSlot> timeSlots = getTimeSlots();
       Doctor doctor = doctorRepository.findById(doctorId)
               .orElseThrow(() -> new EntityNotFoundException("Doctor not found"));
       List<Appointment> appointments = appointmentRepository.findByDateAndDoctor(date, doctor);
       appointments.forEach((appointment -> {
           if (appointment.getDoctor().equals(doctor)) {
               timeSlots.remove(appointment.getTimeSlot());
           }
       }));
       return timeSlots;
    }
}
