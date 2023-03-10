package ro.alexrmn.hospitalmanagerbackend.controller;

import lombok.RequiredArgsConstructor;
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
    private final ObjectValidator<CreateSpecialtyDto> createSpecialtyValidator;

    @GetMapping
    public ResponseEntity<List<SpecialtyDto>> getSpecialties() {
        List<SpecialtyDto> specialties = specialtyService.getSpecialties();
        return ResponseEntity.ok().body(specialties);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Specialty> createSpecialty(@RequestBody CreateSpecialtyDto createSpecialtyDto) {
        createSpecialtyValidator.validate(createSpecialtyDto);
        Specialty specialty = specialtyService.save(createSpecialtyDto);
        return ResponseEntity.ok().body(specialty);
    }
}
