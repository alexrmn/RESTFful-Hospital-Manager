package ro.alexrmn.hospitalmanagerbackend.service;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ro.alexrmn.hospitalmanagerbackend.model.Diagnosis;
import ro.alexrmn.hospitalmanagerbackend.model.dto.DiagnosisDto;
import ro.alexrmn.hospitalmanagerbackend.repository.DiagnosisRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class DiagnosisServiceImplTest {

    @Mock
    private  DiagnosisRepository diagnosisRepository;


    private DiagnosisServiceImpl diagnosisService;

    @BeforeEach
    void setup() {
        diagnosisService = new DiagnosisServiceImpl(diagnosisRepository);
    }

    @Test
    void saveDiagnosisTest() {
        DiagnosisDto diagnosisDto = DiagnosisDto.builder()
                .name("Test diagnosis")
                .build();
        Diagnosis diagnosis = Diagnosis.builder()
                .name(diagnosisDto.getName())
                .build();
        when(diagnosisRepository.save(diagnosis)).thenReturn(diagnosis);
        DiagnosisDto savedDiagnosisDto = diagnosisService.save(diagnosisDto);
        assertEquals(diagnosisDto, savedDiagnosisDto);
    }

    @Test
    void saveDiagnosisTest_withExistingDiagnosis() {
        DiagnosisDto diagnosisDto = DiagnosisDto.builder()
                .name("Test diagnosis")
                .build();
        when(diagnosisRepository.existsByName(diagnosisDto.getName())).thenThrow(new EntityExistsException());
        assertThrows(EntityExistsException.class, () -> diagnosisService.save(diagnosisDto));
    }

    @Test
    void getDiagnosesTest() {
        List<Diagnosis> diagnoses = new ArrayList<>();
        Diagnosis diagnosis1 = Diagnosis.builder().name("Diagnosis1").build();
        Diagnosis diagnosis2 = Diagnosis.builder().name("Diagnosis2").build();
        diagnoses.add(diagnosis1);
        diagnoses.add(diagnosis2);
        List<DiagnosisDto> expected = diagnoses.stream()
                        .map(Diagnosis::toDto).toList();

        when(diagnosisRepository.findAll()).thenReturn(diagnoses);

        List<DiagnosisDto> savedDiagnosesDtos = diagnosisService.getDiagnoses();
        assertEquals(expected, savedDiagnosesDtos);

    }

    @Test
    void deleteDiagnosisTest() {
        Diagnosis diagnosis = Diagnosis.builder()
                .id(5L)
                .name("Test diagnosis")
                .build();
        when(diagnosisRepository.findById(diagnosis.getId())).thenReturn(Optional.of(diagnosis));
        diagnosisService.deleteDiagnosis(diagnosis.getId());
        verify(diagnosisRepository, Mockito.times(1)).delete(diagnosis);
    }

    @Test
    void getDiagnosisTest_withExistingDiagnosis() {
        Diagnosis diagnosis = Diagnosis.builder()
                .id(5L)
                .name("Test Diagnosis")
                .build();
        when(diagnosisRepository.findById(diagnosis.getId())).thenReturn(Optional.of(diagnosis));
        DiagnosisDto retrievedDiagnosisDto = diagnosisService.getDiagnosis(diagnosis.getId());
        assertEquals(diagnosis.toDto(), retrievedDiagnosisDto);
    }

    @Test
    void getDiagnosisTest_withNonExistingDiagnosis() {
        Diagnosis diagnosis = Diagnosis.builder()
                .id(5L)
                .name("Test Diagnosis")
                .build();
        when(diagnosisRepository.findById(diagnosis.getId())).thenThrow(new EntityNotFoundException());
        assertThrows(EntityNotFoundException.class, () -> diagnosisService.getDiagnosis(diagnosis.getId()));
    }

    @Test
    void updateExistingDiagnosisTest() {
        Long diagnosisId = 1L;
        DiagnosisDto diagnosisDto = DiagnosisDto.builder()
                .name("Updated diagnosis name")
                .build();
        Diagnosis existingDiagnosis = new Diagnosis();
        existingDiagnosis.setId(diagnosisId);
        existingDiagnosis.setName("Test diagnosis");
        when(diagnosisRepository.findById(diagnosisId)).thenReturn(Optional.of(existingDiagnosis));
        when(diagnosisRepository.save(existingDiagnosis)).thenReturn(existingDiagnosis);

        DiagnosisDto updatedDiagnosisDto = diagnosisService.update(diagnosisId, diagnosisDto);
        assertEquals(diagnosisDto, updatedDiagnosisDto);

    }

    @Test
    void updateNonExistingDiagnosisTest() {
        Long diagnosisId = 1L;
        DiagnosisDto diagnosisDto = DiagnosisDto.builder()
                .name("Updated diagnosis name")
                .build();
        Diagnosis existingDiagnosis = new Diagnosis();
        existingDiagnosis.setId(diagnosisId);
        existingDiagnosis.setName("Test diagnosis");
        when(diagnosisRepository.findById(diagnosisId)).thenThrow(new EntityNotFoundException());
        assertThrows(EntityNotFoundException.class, () -> diagnosisService.update(diagnosisId, diagnosisDto));

    }
}