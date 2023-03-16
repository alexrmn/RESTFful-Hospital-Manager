package ro.alexrmn.hospitalmanagerbackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ro.alexrmn.hospitalmanagerbackend.model.dto.DiagnosisDto;
import ro.alexrmn.hospitalmanagerbackend.service.DiagnosisService;
import ro.alexrmn.hospitalmanagerbackend.validators.ObjectValidator;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/diagnoses")
@CrossOrigin(origins = "*")
public class DiagnosisController {

    private final DiagnosisService diagnosisService;
    private final ObjectValidator<DiagnosisDto> validator;


    @GetMapping("/{diagnosisId}")
    public ResponseEntity<DiagnosisDto> getDiagnosis(@PathVariable Long diagnosisId){
        DiagnosisDto diagnosisDto = diagnosisService.getDiagnosis(diagnosisId);
        return ResponseEntity.ok().body(diagnosisDto);
    }


    @GetMapping
    public ResponseEntity<List<DiagnosisDto>> getDiagnoses() {
        List<DiagnosisDto> diagnosisDtoList = diagnosisService.getDiagnoses();
        return ResponseEntity.ok().body(diagnosisDtoList);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DiagnosisDto> createDiagnosis(@RequestBody DiagnosisDto diagnosisDto) {
        validator.validate(diagnosisDto);
        DiagnosisDto savedDiagnosis = diagnosisService.save(diagnosisDto);
        return ResponseEntity.ok().body(savedDiagnosis);
    }

    @DeleteMapping("/{diagnosisId}")
    @PreAuthorize("hasRole('ADMIN')")
    public HttpStatus deleteDiagnosis(@PathVariable Long diagnosisId){
        diagnosisService.deleteDiagnosis(diagnosisId);
        return HttpStatus.ACCEPTED;
    }

    @PutMapping("/{diagnosisId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DiagnosisDto> updateDiagnosis(@RequestBody DiagnosisDto diagnosisDto,
                                                        @PathVariable Long diagnosisId) {
        validator.validate(diagnosisDto);
        DiagnosisDto updatedDiagnosis = diagnosisService.update(diagnosisId, diagnosisDto);
        return  ResponseEntity.ok().body(updatedDiagnosis);
    }

}
