package ro.alexrmn.hospitalmanagerbackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ro.alexrmn.hospitalmanagerbackend.model.dto.ProcedureDto;
import ro.alexrmn.hospitalmanagerbackend.service.ProcedureService;
import ro.alexrmn.hospitalmanagerbackend.validators.ObjectValidator;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/procedures")
@CrossOrigin(origins = "*")
public class ProcedureController {

    private final ProcedureService procedureService;
    private final ObjectValidator<ProcedureDto> validator;


    @GetMapping("/{procedureId}")
    public ResponseEntity<ProcedureDto> getProcedure(@PathVariable Long procedureId){
        ProcedureDto procedureDto = procedureService.getProcedure(procedureId);
        return ResponseEntity.ok().body(procedureDto);
    }


    @GetMapping
    public ResponseEntity<List<ProcedureDto>> getProcedures() {
        List<ProcedureDto> procedureDtoList = procedureService.getProcedures();
        return ResponseEntity.ok().body(procedureDtoList);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProcedureDto> createProcedure(@RequestBody ProcedureDto procedureDto) {
        validator.validate(procedureDto);
        ProcedureDto savedProcedureDto = procedureService.save(procedureDto);
        return ResponseEntity.ok().body(savedProcedureDto);
    }

    @DeleteMapping("/{procedureId}")
    @PreAuthorize("hasRole('ADMIN')")
    public HttpStatus deleteProcedure(@PathVariable Long procedureId){
        procedureService.deleteProcedure(procedureId);
        return HttpStatus.ACCEPTED;
    }

    @PutMapping("/{procedureId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProcedureDto> updateProcedure(@RequestBody ProcedureDto procedureDto,
                                                        @PathVariable Long procedureId) {
        validator.validate(procedureDto);
        ProcedureDto updatedProcedureDto = procedureService.update(procedureId, procedureDto);
        return  ResponseEntity.ok().body(updatedProcedureDto);
    }

}

