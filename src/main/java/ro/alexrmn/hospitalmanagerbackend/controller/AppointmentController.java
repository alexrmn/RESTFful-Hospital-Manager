package ro.alexrmn.hospitalmanagerbackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.alexrmn.hospitalmanagerbackend.Validators.ObjectValidator;
import ro.alexrmn.hospitalmanagerbackend.model.Appointment;
import ro.alexrmn.hospitalmanagerbackend.model.dto.CreateAppointmentDto;

import ro.alexrmn.hospitalmanagerbackend.service.AppointmentService;

@RestController
@RequestMapping("/appointments")
@CrossOrigin
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final ObjectValidator<CreateAppointmentDto> createAppointmentValidator;

    @PostMapping
    public ResponseEntity<?> createAppointment(@RequestBody CreateAppointmentDto createAppointmentDto) {
        createAppointmentValidator.validate(createAppointmentDto);
        Appointment appointment = appointmentService.saveAppointment(createAppointmentDto);
        return ResponseEntity.ok().body(appointment);
    }


    @GetMapping("/{appointmentId}")
    public ResponseEntity<Appointment> getAppointment(@PathVariable Long appointmentId){
       Appointment appointment = appointmentService.getAppointment(appointmentId);
        return ResponseEntity.ok().body(appointment);
    }
}
