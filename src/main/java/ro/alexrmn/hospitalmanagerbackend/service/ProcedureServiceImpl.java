package ro.alexrmn.hospitalmanagerbackend.service;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.alexrmn.hospitalmanagerbackend.model.Procedure;
import ro.alexrmn.hospitalmanagerbackend.model.dto.ProcedureDto;
import ro.alexrmn.hospitalmanagerbackend.repository.ProcedureRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProcedureServiceImpl implements ProcedureService{

    private final ProcedureRepository procedureRepository;

    @Override
    public ProcedureDto save(ProcedureDto procedureDto) {
        if(procedureRepository.existsByName(procedureDto.getName())) {
            throw  new EntityExistsException("Diagnoses with that name already exists!");
        }
        Procedure procedure = Procedure.builder()
                .name(procedureDto.getName())
                .build();
        return procedureRepository.save(procedure).toDto();
    }

    @Override
    public List<ProcedureDto> getProcedures() {
        return procedureRepository.findAll()
                .stream()
                .map(Procedure::toDto)
                .toList();
    }

    @Override
    public void deleteProcedure(Long procedureId) {
        Procedure procedure = procedureRepository.findById(procedureId)
                .orElseThrow(() -> new EntityNotFoundException("Procedure not found"));
        procedureRepository.delete(procedure);
    }

    @Override
    public ProcedureDto getProcedure(Long procedureId) {
        Procedure procedure = procedureRepository.findById(procedureId)
                .orElseThrow(() -> new EntityNotFoundException("Procedure not found"));
        return procedure.toDto();
    }

    @Override
    public ProcedureDto update(Long procedureId, ProcedureDto procedureDto) {
        Procedure procedure = procedureRepository.findById(procedureId)
                .orElseThrow(() -> new EntityNotFoundException("Procedure not found"));
        procedure.setName(procedureDto.getName());
        return procedureRepository.save(procedure).toDto();
    }
}