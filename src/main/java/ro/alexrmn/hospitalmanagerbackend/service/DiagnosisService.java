package ro.alexrmn.hospitalmanagerbackend.service;

import ro.alexrmn.hospitalmanagerbackend.model.dto.DiagnosisDto;

import java.util.List;

public interface DiagnosisService {
    DiagnosisDto save(DiagnosisDto diagnosisDto);

    List<DiagnosisDto> getDiagnoses();

    void deleteDiagnosis(Long diagnosisId);

    DiagnosisDto getDiagnosis(Long diagnosisId);

    DiagnosisDto update(Long diagnosisId, DiagnosisDto diagnosisDto);
}
