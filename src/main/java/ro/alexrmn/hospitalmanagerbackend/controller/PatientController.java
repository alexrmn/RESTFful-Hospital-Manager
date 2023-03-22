package ro.alexrmn.hospitalmanagerbackend.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ro.alexrmn.hospitalmanagerbackend.validators.ObjectValidator;
import ro.alexrmn.hospitalmanagerbackend.model.Patient;
import ro.alexrmn.hospitalmanagerbackend.model.dto.CreatePatientDto;
import ro.alexrmn.hospitalmanagerbackend.model.dto.PatientDto;
import ro.alexrmn.hospitalmanagerbackend.service.PatientService;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/patients")
@CrossOrigin
public class PatientController {

    private final PatientService patientService;
    private final ObjectValidator<CreatePatientDto> createPatientValidator;
    private final ObjectValidator<PatientDto> editPatientValidator;

    @GetMapping("/{patientId}")
    public ResponseEntity<PatientDto> getPatient(@PathVariable Long patientId){

        PatientDto patientDto = patientService.getPatient(patientId);
        return ResponseEntity.ok().body(patientDto);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_DOCTOR','ROLE_ADMIN')")
    public ResponseEntity<List<PatientDto>> getPatients() {
        List<PatientDto> patientDtoList = patientService.getPatients();
        return ResponseEntity.ok().body(patientDtoList);
    }
    

    @PostMapping
    public ResponseEntity<PatientDto> createPatient(@RequestBody CreatePatientDto createPatientDto){
        createPatientValidator.validate(createPatientDto);
        PatientDto savedPatientDto = patientService.savePatient(createPatientDto);
        return  ResponseEntity.ok().body(savedPatientDto);
    }

    @PutMapping("/{patientId}")
    public ResponseEntity<PatientDto> updatePatient(@RequestBody PatientDto patientDto,
                                                 @PathVariable Long patientId){

        editPatientValidator.validate(patientDto);
        PatientDto updatePatientDto = patientService.updatePatient(patientId, patientDto);
        return ResponseEntity.accepted().body(updatePatientDto);
    }

    @DeleteMapping("/{patientId}")
    public HttpStatus deletePatient(@PathVariable Long patientId){

        patientService.deletePatient(patientId);
        return HttpStatus.ACCEPTED;
    }

}
