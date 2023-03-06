package ro.alexrmn.hospitalmanagerbackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ro.alexrmn.hospitalmanagerbackend.Validators.ObjectValidator;
import ro.alexrmn.hospitalmanagerbackend.model.Doctor;
import ro.alexrmn.hospitalmanagerbackend.model.dto.CreateDoctorDto;
import ro.alexrmn.hospitalmanagerbackend.model.dto.DoctorDto;
import ro.alexrmn.hospitalmanagerbackend.service.DoctorService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/doctors")
@CrossOrigin
public class DoctorController {

    private final DoctorService doctorService;
    private final ObjectValidator<CreateDoctorDto> createDoctorValidator;
    private final ObjectValidator<DoctorDto> editDoctorValidator;

    @GetMapping("/{doctorUsername}")
    public ResponseEntity<CreateDoctorDto> getDoctor(@PathVariable String doctorUsername){
        CreateDoctorDto createDoctorDto = doctorService.getDoctor(doctorUsername);
        return ResponseEntity.ok().body(createDoctorDto);
    }

    @GetMapping
    public ResponseEntity<List<CreateDoctorDto>> getDoctors() {
        List<CreateDoctorDto> createDoctorDtoList = doctorService.getDoctors();
        return ResponseEntity.ok().body(createDoctorDtoList);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Doctor> createDoctor(@RequestBody CreateDoctorDto createDoctorDto) {
        createDoctorValidator.validate(createDoctorDto);
        Doctor doctor = doctorService.saveDoctor(createDoctorDto);
        return ResponseEntity.accepted().body(doctor);
    }

    @PutMapping("/{doctorUsername}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Doctor> updateDoctor
            (@RequestBody DoctorDto doctorDto, @PathVariable String doctorUsername){
        editDoctorValidator.validate(doctorDto);
        Doctor doctor = doctorService.updateDoctor(doctorUsername, doctorDto);
        return ResponseEntity.accepted().body(doctor);
    }

    @DeleteMapping("/{doctorUsername}")
    @PreAuthorize("hasRole('ADMIN')")
    public HttpStatus deleteDoctor(@PathVariable String doctorUsername){
        doctorService.deleteDoctor(doctorUsername);
        return HttpStatus.ACCEPTED;
    }







}
