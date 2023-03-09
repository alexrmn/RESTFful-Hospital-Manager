package ro.alexrmn.hospitalmanagerbackend.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.alexrmn.hospitalmanagerbackend.model.Appointment;
import ro.alexrmn.hospitalmanagerbackend.model.Doctor;
import ro.alexrmn.hospitalmanagerbackend.model.dto.AppointmentDto;
import ro.alexrmn.hospitalmanagerbackend.model.dto.CreateAppointmentDto;
import ro.alexrmn.hospitalmanagerbackend.repository.AppointmentRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;


    public Appointment saveAppointment(CreateAppointmentDto createAppointmentDto) {

        Appointment appointment = Appointment.builder()
                .date(createAppointmentDto.getDate())
                .doctor(createAppointmentDto.getDoctor())
                .specialty(createAppointmentDto.getSpecialty())
                .patient(createAppointmentDto.getPatient())
                .build();
        return appointmentRepository.save(appointment);

    }

    @Override
    public AppointmentDto getAppointment(Long appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new EntityNotFoundException("Appointment not found"));
        return appointment.toDto();
    }
}
