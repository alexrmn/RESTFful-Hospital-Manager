package ro.alexrmn.hospitalmanagerbackend.service;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.alexrmn.hospitalmanagerbackend.model.Diagnosis;
import ro.alexrmn.hospitalmanagerbackend.model.Specialty;
import ro.alexrmn.hospitalmanagerbackend.model.dto.DiagnosisDto;
import ro.alexrmn.hospitalmanagerbackend.repository.DiagnosisRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiagnosisServiceImpl implements DiagnosisService{

    private  final DiagnosisRepository diagnosisRepository;
    @Override
    public DiagnosisDto save(DiagnosisDto diagnosisDto) {
        if(diagnosisRepository.existsByName(diagnosisDto.getName())) {
            throw  new EntityExistsException("Diagnoses with that name already exists!");
        }
        Diagnosis diagnosis = Diagnosis.builder()
                .name(diagnosisDto.getName())
                .build();
        return diagnosisRepository.save(diagnosis).toDto();
    }

    @Override
    public List<DiagnosisDto> getDiagnoses() {
        return diagnosisRepository.findAll()
                .stream()
                .map(Diagnosis::toDto)
                .toList();
    }

    @Override
    public void deleteDiagnosis(Long diagnosisId) {
        Diagnosis diagnosis = diagnosisRepository.findById(diagnosisId)
                .orElseThrow(() -> new EntityNotFoundException("Diagnosis not found"));
        diagnosisRepository.delete(diagnosis);

    }

    @Override
    public DiagnosisDto getDiagnosis(Long diagnosisId) {
        Diagnosis diagnosis = diagnosisRepository.findById(diagnosisId)
                .orElseThrow(() -> new EntityNotFoundException("Diagnosis not found"));
        return diagnosis.toDto();
    }

    @Override
    public DiagnosisDto update(Long diagnosisId, DiagnosisDto diagnosisDto) {
        Diagnosis diagnosis = diagnosisRepository.findById(diagnosisId)
                .orElseThrow(() -> new EntityNotFoundException("Diagnosis not found"));
        diagnosis.setName(diagnosisDto.getName());
        return diagnosisRepository.save(diagnosis).toDto();
    }
}
