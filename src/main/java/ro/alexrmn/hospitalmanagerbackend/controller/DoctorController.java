package ro.alexrmn.hospitalmanagerbackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ro.alexrmn.hospitalmanagerbackend.validators.ObjectValidator;
import ro.alexrmn.hospitalmanagerbackend.model.Doctor;
import ro.alexrmn.hospitalmanagerbackend.model.dto.CreateDoctorDto;
import ro.alexrmn.hospitalmanagerbackend.model.dto.DoctorDto;
import ro.alexrmn.hospitalmanagerbackend.repository.SpecialtyRepository;
import ro.alexrmn.hospitalmanagerbackend.service.DoctorService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/doctors")
@CrossOrigin(origins = "*")
public class DoctorController {

    private final DoctorService doctorService;
    private final SpecialtyRepository specialtyRepository;
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
    public ResponseEntity<DoctorDto> createDoctor(@RequestBody CreateDoctorDto createDoctorDto) {
        createDoctorValidator.validate(createDoctorDto);
        DoctorDto savedDoctorDto = doctorService.saveDoctor(createDoctorDto);
        return ResponseEntity.accepted().body(savedDoctorDto);
    }

    @PutMapping("/{doctorId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DoctorDto> updateDoctor
            (@RequestBody DoctorDto doctorDto, @PathVariable Long doctorId){
        editDoctorValidator.validate(doctorDto);
        DoctorDto updatedDoctorDto = doctorService.updateDoctor(doctorId, doctorDto);
        return ResponseEntity.accepted().body(updatedDoctorDto);
    }

    @DeleteMapping("/{doctorId}")
    @PreAuthorize("hasRole('ADMIN')")
    public HttpStatus deleteDoctor(@PathVariable Long doctorId){
        doctorService.deleteDoctor(doctorId);
        return HttpStatus.ACCEPTED;
    }

    @GetMapping("/specialty/{specialtyId}")
    public ResponseEntity<List<DoctorDto>> getDoctorsBySpecialty(@PathVariable Long specialtyId) {
        List<DoctorDto> doctorDtoList = doctorService.findBySpecialty(specialtyId);
        return ResponseEntity.ok().body(doctorDtoList);
    }
}
