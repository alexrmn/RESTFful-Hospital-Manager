package ro.alexrmn.hospitalmanagerbackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.alexrmn.hospitalmanagerbackend.Validators.ObjectValidator;
import ro.alexrmn.hospitalmanagerbackend.model.Appointment;
import ro.alexrmn.hospitalmanagerbackend.model.dto.AppointmentDto;
import ro.alexrmn.hospitalmanagerbackend.model.dto.CreateAppointmentDto;

import ro.alexrmn.hospitalmanagerbackend.service.AppointmentService;
import ro.alexrmn.hospitalmanagerbackend.service.AppointmentServiceImpl;

@RestController
@RequestMapping("/appointments")
@CrossOrigin
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentServiceImpl appointmentService;
    private final ObjectValidator<CreateAppointmentDto> createAppointmentValidator;

    @PostMapping
    public ResponseEntity<?> createAppointment() {
        Appointment appointment = appointmentService.createAppointment();
        return ResponseEntity.ok().body(appointment);
    }



}
