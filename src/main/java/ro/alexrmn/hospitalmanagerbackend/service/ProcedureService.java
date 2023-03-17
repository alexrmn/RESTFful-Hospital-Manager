package ro.alexrmn.hospitalmanagerbackend.service;

import ro.alexrmn.hospitalmanagerbackend.model.dto.ProcedureDto;

import java.util.List;

public interface ProcedureService {
    ProcedureDto save(ProcedureDto procedureDto);

    List<ProcedureDto> getProcedures();

    void deleteProcedure(Long procedureId);

    ProcedureDto getProcedure(Long procedureId);

    ProcedureDto update(Long procedureId, ProcedureDto procedureDto);
}