package ro.alexrmn.hospitalmanagerbackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ro.alexrmn.hospitalmanagerbackend.service.AppointmentService;
import ro.alexrmn.hospitalmanagerbackend.validators.ObjectValidator;
import ro.alexrmn.hospitalmanagerbackend.model.dto.AppointmentDto;
import ro.alexrmn.hospitalmanagerbackend.model.dto.CreateAppointmentDto;



import java.util.List;

@RestController
@RequestMapping("/appointments")
@CrossOrigin
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final ObjectValidator<CreateAppointmentDto> createAppointmentValidator;

    @PostMapping
    public ResponseEntity<AppointmentDto> createAppointment(@RequestBody CreateAppointmentDto createAppointmentDto) {
        createAppointmentValidator.validate(createAppointmentDto);
        AppointmentDto appointmentDto = appointmentService.createAppointment(createAppointmentDto);
        return ResponseEntity.ok().body(appointmentDto);
    }

    @GetMapping("/upcoming/{patientId}")
    public ResponseEntity<List<AppointmentDto>> getUpcomingAppointments(@PathVariable Long patientId) {
        List<AppointmentDto> appointmentDtoList = appointmentService.getUpcomingAppointments(patientId);
        return ResponseEntity.ok().body(appointmentDtoList);
    }

    @GetMapping("/history/{patientId}")
    public ResponseEntity<List<AppointmentDto>> getAppointmentHistory(@PathVariable Long patientId) {
        List<AppointmentDto> appointmentDtoList = appointmentService.getAppointmentHistory(patientId);
        return ResponseEntity.ok().body(appointmentDtoList);
    }

    @DeleteMapping("/{appointmentId}")
    public HttpStatus deleteAppointment(@PathVariable Long appointmentId) {
        appointmentService.deleteAppointment(appointmentId);
        return HttpStatus.ACCEPTED;
    }

    @PutMapping("/{appointmentId}")
    public ResponseEntity<AppointmentDto>  updateAppointmentDetails
            (@RequestBody CreateAppointmentDto createAppointmentDto, @PathVariable Long appointmentId) {
        createAppointmentValidator.validate(createAppointmentDto);
        AppointmentDto appointmentDto = appointmentService.updateAppointment(appointmentId, createAppointmentDto);
        return ResponseEntity.ok().body(appointmentDto);
    }

    @GetMapping("/scheduled/{doctorId}")
    @PreAuthorize("hasAnyRole('ROLE_DOCTOR','ROLE_ADMIN')")
    public ResponseEntity<List<AppointmentDto>> getAppointmentsByDoctor(@PathVariable Long doctorId) {
        List<AppointmentDto> appointmentDtoList = appointmentService.getAppointmentsByDoctor(doctorId);
        return ResponseEntity.ok().body(appointmentDtoList);
    }

    @GetMapping("{appointmentId}")
    @PreAuthorize("hasAnyRole('ROLE_DOCTOR','ROLE_ADMIN')")
    public ResponseEntity<AppointmentDto> getAppointment(@PathVariable Long appointmentId) {
        AppointmentDto appointmentDto = appointmentService.getAppointment(appointmentId);
        return ResponseEntity.ok().body(appointmentDto);
    }


}
