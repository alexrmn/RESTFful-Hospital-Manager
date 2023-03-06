package ro.alexrmn.hospitalmanagerbackend.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ro.alexrmn.hospitalmanagerbackend.Validators.ObjectValidator;
import ro.alexrmn.hospitalmanagerbackend.exception.NotAuthorizedToViewResourceException;
import ro.alexrmn.hospitalmanagerbackend.model.Patient;
import ro.alexrmn.hospitalmanagerbackend.model.dto.CreatePatientDto;
import ro.alexrmn.hospitalmanagerbackend.model.dto.PatientDto;
import ro.alexrmn.hospitalmanagerbackend.service.PatientService;
import ro.alexrmn.hospitalmanagerbackend.utils.AuthUtils;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/patients")
@CrossOrigin
public class PatientController {

    private final PatientService patientService;
    private final ObjectValidator<CreatePatientDto> createPatientValidator;
    private final ObjectValidator<PatientDto> editPatientValidator;

    @GetMapping("/{patientUsername}")
    public ResponseEntity<CreatePatientDto> getPatient(@PathVariable String patientUsername){
        boolean isUserOwnerOfResource = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName()
                .equals(patientUsername);

        if(!isUserOwnerOfResource && !AuthUtils.isLoggedInUserAdmin()) {
            throw new NotAuthorizedToViewResourceException("Not authorized to view that resource");
        }

        CreatePatientDto createPatientDto = patientService.getPatient(patientUsername);
        return ResponseEntity.ok().body(createPatientDto);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<CreatePatientDto>> getPatients() {
        List<CreatePatientDto> createPatientDtoList = patientService.getPatients();
        return ResponseEntity.ok().body(createPatientDtoList);
    }

    @PostMapping
    public ResponseEntity<Patient> createPatient(@RequestBody CreatePatientDto createPatientDto){
        createPatientValidator.validate(createPatientDto);
        Patient patient = patientService.savePatient(createPatientDto);
        return  ResponseEntity.ok().body(patient);
    }

    @PutMapping("/{patientUsername}")
    public ResponseEntity<Patient> updatePatient(@RequestBody PatientDto patientDto,
                                                 @PathVariable String patientUsername){

        boolean isUserOwnerOfResource = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName()
                .equals(patientUsername);

        if(!isUserOwnerOfResource && !AuthUtils.isLoggedInUserAdmin()) {
            throw new NotAuthorizedToViewResourceException("Not authorized to view that resource");
        }
        editPatientValidator.validate(patientDto);
        Patient patient = patientService.updatePatient(patientUsername, patientDto);
        return ResponseEntity.accepted().body(patient);
    }

    @DeleteMapping("{patientUsername}")
    public HttpStatus deletePatient(@PathVariable String patientUsername){
        boolean isUserOwnerOfResource = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName()
                .equals(patientUsername);

        if(!isUserOwnerOfResource && !AuthUtils.isLoggedInUserAdmin()) {
            throw new NotAuthorizedToViewResourceException("Not authorized to view that resource");
        }
        patientService.deletePatient(patientUsername);
        return HttpStatus.ACCEPTED;
    }

}
