package ro.alexrmn.hospitalmanagerbackend.service;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ro.alexrmn.hospitalmanagerbackend.model.ERole;
import ro.alexrmn.hospitalmanagerbackend.model.Patient;
import ro.alexrmn.hospitalmanagerbackend.model.Role;
import ro.alexrmn.hospitalmanagerbackend.model.dto.CreatePatientDto;
import ro.alexrmn.hospitalmanagerbackend.model.dto.PatientDto;
import ro.alexrmn.hospitalmanagerbackend.repository.PatientRepository;
import ro.alexrmn.hospitalmanagerbackend.repository.RoleRepository;
import ro.alexrmn.hospitalmanagerbackend.repository.UserRepository;


import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final RoleRepository roleRepository;

    @Override
    public PatientDto savePatient(CreatePatientDto createPatientDto) {
        if(userRepository.existsByUsername(createPatientDto.getUsername())) {
            throw new EntityExistsException("Couldn't create account. A user with that username already exists");
        }
        if(userRepository.existsByEmail(createPatientDto.getEmail())) {
            throw new EntityExistsException("Couldn't create account. A user with that email already exists");
        }
        Role role = roleRepository.findByName(ERole.ROLE_PATIENT)
                .orElseThrow(() -> new EntityNotFoundException("Error: Role is not found."));

        Patient patient = Patient.builder()
                .firstName(createPatientDto.getFirstName())
                .lastName(createPatientDto.getLastName())
                .username(createPatientDto.getUsername())
                .email(createPatientDto.getEmail())
                .password(encoder.encode(createPatientDto.getPassword()))
                .phoneNumber(createPatientDto.getPhoneNumber())
                .roles(Set.of(role))
                .build();

        return patientRepository.save(patient).toDto();
    }

    @Override
    public PatientDto getPatient(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found"));
        return patient.toDto();
    }

    @Override
    public List<PatientDto> getPatients() {
        List<Patient> patients = patientRepository.findAll();
        return patients.stream().map(Patient::toDto).toList();
    }

    @Override
    public PatientDto updatePatient(Long id, PatientDto patientDto) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found"));

        patient.setFirstName(patientDto.getFirstName());
        patient.setLastName(patientDto.getLastName());
        patient.setPhoneNumber(patientDto.getPhoneNumber());
        return patientRepository.save(patient).toDto();
    }

    @Override
    public void deletePatient(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found"));
        patientRepository.delete(patient);
    }
}
