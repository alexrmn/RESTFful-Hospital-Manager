package ro.alexrmn.hospitalmanagerbackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ro.alexrmn.hospitalmanagerbackend.Validators.ObjectValidator;
import ro.alexrmn.hospitalmanagerbackend.model.Specialty;
import ro.alexrmn.hospitalmanagerbackend.model.dto.CreateSpecialtyDto;
import ro.alexrmn.hospitalmanagerbackend.model.dto.PatientDto;
import ro.alexrmn.hospitalmanagerbackend.model.dto.SpecialtyDto;
import ro.alexrmn.hospitalmanagerbackend.service.SpecialtyService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/specialties")
@CrossOrigin(origins = "*")
public class SpecialtyController {

    private final SpecialtyService specialtyService;
    private final ObjectValidator<SpecialtyDto> specialtyValidator;

    @GetMapping
    public ResponseEntity<List<SpecialtyDto>> getSpecialties() {
        List<SpecialtyDto> specialties = specialtyService.getSpecialties();
        return ResponseEntity.ok().body(specialties);
    }

    @GetMapping("/{specialtyId}")
    public ResponseEntity<SpecialtyDto> getSpecialty(@PathVariable Long specialtyId) {
        SpecialtyDto specialtyDto = specialtyService.getSpecialty(specialtyId);
        return ResponseEntity.ok().body(specialtyDto);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Specialty> createSpecialty(@RequestBody SpecialtyDto specialtyDto) {
        specialtyValidator.validate(specialtyDto);
        Specialty specialty = specialtyService.save(specialtyDto);
        return ResponseEntity.ok().body(specialty);
    }

    @DeleteMapping("/{specialtyId}")
    @PreAuthorize("hasRole('ADMIN')")
    public HttpStatus deleteSpecialty(@PathVariable Long specialtyId) {
        specialtyService.delete(specialtyId);
        return  HttpStatus.ACCEPTED;
    }

    @PutMapping("/{specialtyId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Specialty> updateSpecialty(@RequestBody SpecialtyDto specialtyDto, @PathVariable Long specialtyId) {
        specialtyValidator.validate(specialtyDto);
        Specialty specialty = specialtyService.update(specialtyId, specialtyDto);
        return ResponseEntity.ok().body(specialty);
    }

}
