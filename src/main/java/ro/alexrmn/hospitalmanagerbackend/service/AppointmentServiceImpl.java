package ro.alexrmn.hospitalmanagerbackend.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.alexrmn.hospitalmanagerbackend.exception.ObjectNotValidException;
import ro.alexrmn.hospitalmanagerbackend.model.Appointment;
import ro.alexrmn.hospitalmanagerbackend.model.Doctor;
import ro.alexrmn.hospitalmanagerbackend.model.TimeSlot;
import ro.alexrmn.hospitalmanagerbackend.model.dto.CreateAppointmentDto;
import ro.alexrmn.hospitalmanagerbackend.repository.AppointmentRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;

    public boolean isTimeSlotAvailable(LocalDate date, TimeSlot timeSlot, Doctor doctor) {
        List<Appointment> appointments = appointmentRepository.findByDateAndDoctor(date, doctor);

        for (Appointment appointment : appointments) {
            if (appointment.getTimeSlot() == timeSlot) {
                return false;
            }
        }
        return true;
    }

    public Appointment saveAppointment(CreateAppointmentDto createAppointmentDto) {
        if (!isTimeSlotAvailable(createAppointmentDto.getDate(), createAppointmentDto.getTimeSlot(), createAppointmentDto.getDoctor())) {
            throw new RuntimeException("Timeslot for that doctor/date is not available");
        } else {
            Appointment appointment = Appointment.builder()
                    .date(createAppointmentDto.getDate())
                    .timeSlot(createAppointmentDto.getTimeSlot())
                    .doctor(createAppointmentDto.getDoctor())
                    .specialty(createAppointmentDto.getSpecialty())
                    .patient(createAppointmentDto.getPatient())
                    .build();
            return appointmentRepository.save(appointment);
        }
    }

    @Override
    public Appointment getAppointment(Long appointmentId) {
        return appointmentRepository.findById(appointmentId)
                .orElseThrow(() ->new EntityNotFoundException("Appointment not found"));
    }
}
