package ro.alexrmn.hospitalmanagerbackend.service;

import ro.alexrmn.hospitalmanagerbackend.model.Patient;
import ro.alexrmn.hospitalmanagerbackend.model.dto.CreatePatientDto;
import ro.alexrmn.hospitalmanagerbackend.model.dto.PatientDto;

import java.util.List;

public interface PatientService {

    Patient savePatient(CreatePatientDto createPatientDto);

    CreatePatientDto getPatient(String username);

    List<CreatePatientDto> getPatients();

    Patient updatePatient(String username, PatientDto patientDto);

    void deletePatient(String patientUsername);
}
