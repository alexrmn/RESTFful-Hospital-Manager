package ro.alexrmn.hospitalmanagerbackend.service;

import ro.alexrmn.hospitalmanagerbackend.model.Appointment;
import ro.alexrmn.hospitalmanagerbackend.model.dto.AppointmentDto;
import ro.alexrmn.hospitalmanagerbackend.model.dto.CreateAppointmentDto;

import java.util.List;

public interface AppointmentService {

    AppointmentDto createAppointment(CreateAppointmentDto createAppointmentDto);

    List<AppointmentDto> getUpcomingAppointments(Long patientId);

    List<AppointmentDto> getAppointmentHistory(Long patientId);

    void deleteAppointment(Long appointmentId);

    AppointmentDto updateAppointment(Long appointmentId, CreateAppointmentDto createAppointmentDto);

    List<AppointmentDto> getAppointmentsByDoctor(Long doctorId);
}
