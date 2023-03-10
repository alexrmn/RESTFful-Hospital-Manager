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
@CrossOrigin(origins = "*")
public class DoctorController {

    private final DoctorService doctorService;
    private final ObjectValidator<CreateDoctorDto> createDoctorValidator;
    private final ObjectValidator<DoctorDto> editDoctorValidator;

    @GetMapping("/{doctorId}")
    public ResponseEntity<DoctorDto> getDoctor(@PathVariable Long doctorId){
        DoctorDto doctorDto = doctorService.getDoctor(doctorId);
        return ResponseEntity.ok().body(doctorDto);
    }

    @GetMapping
    public ResponseEntity<List<DoctorDto>> getDoctors() {
        List<DoctorDto> doctorDtoList = doctorService.getDoctors();
        return ResponseEntity.ok().body(doctorDtoList);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Doctor> createDoctor(@RequestBody CreateDoctorDto createDoctorDto) {
        createDoctorValidator.validate(createDoctorDto);
        Doctor doctor = doctorService.saveDoctor(createDoctorDto);
        return ResponseEntity.accepted().body(doctor);
    }

    @PutMapping("/{doctorId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Doctor> updateDoctor
            (@RequestBody DoctorDto doctorDto, @PathVariable Long doctorId){
        editDoctorValidator.validate(doctorDto);
        Doctor doctor = doctorService.updateDoctor(doctorId, doctorDto);
        return ResponseEntity.accepted().body(doctor);
    }

    @DeleteMapping("/{doctorId}")
    @PreAuthorize("hasRole('ADMIN')")
    public HttpStatus deleteDoctor(@PathVariable Long doctorId){
        doctorService.deleteDoctor(doctorId);
        return HttpStatus.ACCEPTED;
    }

}
