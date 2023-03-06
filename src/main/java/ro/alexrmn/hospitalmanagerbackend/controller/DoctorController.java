package ro.alexrmn.hospitalmanagerbackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ro.alexrmn.hospitalmanagerbackend.Validators.ObjectValidator;
import ro.alexrmn.hospitalmanagerbackend.model.Doctor;
import ro.alexrmn.hospitalmanagerbackend.model.dto.DoctorDto;
import ro.alexrmn.hospitalmanagerbackend.payload.response.MessageResponse;
import ro.alexrmn.hospitalmanagerbackend.service.DoctorService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/doctors")
@CrossOrigin
public class DoctorController {

    private final DoctorService doctorService;
    private final ObjectValidator<DoctorDto> validator;

    @GetMapping("/{doctorUsername}")
    public ResponseEntity<DoctorDto> getDoctor(@PathVariable String doctorUsername){
        DoctorDto doctorDto = doctorService.getDoctor(doctorUsername);
        return ResponseEntity.ok().body(doctorDto);
    }

    @GetMapping
    public ResponseEntity<List<DoctorDto>> getDoctors() {
        List<DoctorDto> doctorDtoList = doctorService.getDoctors();
        return ResponseEntity.ok().body(doctorDtoList);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Doctor> createDoctor(@RequestBody DoctorDto doctorDto) {
        validator.validate(doctorDto);
        Doctor doctor = doctorService.saveDoctor(doctorDto);
        return ResponseEntity.accepted().body(doctor);
    }

    @PutMapping("/{doctorUsername}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Doctor> updateDoctor
            (@RequestBody DoctorDto doctorDto, @PathVariable String doctorUsername){
        validator.validate(doctorDto);
        Doctor doctor = doctorService.updateDoctor(doctorDto);
        return ResponseEntity.accepted().body(doctor);
    }

    @DeleteMapping("/{doctorUsername}")
    @PreAuthorize("hasRole('ADMIN')")
    public HttpStatus deleteDoctor(@PathVariable String doctorUsername){
        doctorService.deleteDoctor(doctorUsername);
        return HttpStatus.ACCEPTED;
    }







}
