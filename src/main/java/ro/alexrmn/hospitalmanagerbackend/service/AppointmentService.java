package ro.alexrmn.hospitalmanagerbackend.service;

import ro.alexrmn.hospitalmanagerbackend.model.Appointment;
import ro.alexrmn.hospitalmanagerbackend.model.dto.AppointmentDto;
import ro.alexrmn.hospitalmanagerbackend.model.dto.CreateAppointmentDto;

public interface AppointmentService {

     Appointment createAppointment(CreateAppointmentDto createAppointmentDto);

}
