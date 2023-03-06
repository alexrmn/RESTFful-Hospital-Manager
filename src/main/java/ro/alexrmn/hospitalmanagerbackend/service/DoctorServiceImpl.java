package ro.alexrmn.hospitalmanagerbackend.service;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ro.alexrmn.hospitalmanagerbackend.model.Doctor;
import ro.alexrmn.hospitalmanagerbackend.model.ERole;
import ro.alexrmn.hospitalmanagerbackend.model.Role;
import ro.alexrmn.hospitalmanagerbackend.model.Specialty;
import ro.alexrmn.hospitalmanagerbackend.model.dto.CreateDoctorDto;
import ro.alexrmn.hospitalmanagerbackend.model.dto.DoctorDto;
import ro.alexrmn.hospitalmanagerbackend.repository.DoctorRepository;
import ro.alexrmn.hospitalmanagerbackend.repository.RoleRepository;
import ro.alexrmn.hospitalmanagerbackend.repository.SpecialtyRepository;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService{

    private final DoctorRepository doctorRepository;
    private final SpecialtyRepository specialtyRepository;
    private final PasswordEncoder encoder;
    private final RoleRepository roleRepository;

    @Override
    public Doctor saveDoctor(CreateDoctorDto createDoctorDto) {
        if(doctorRepository.existsByUsername(createDoctorDto.getUsername())) {
            throw new EntityExistsException("Couldn't create doctor. A user with that username already exists");
        }
        if(doctorRepository.existsByEmail(createDoctorDto.getEmail())) {
            throw new EntityExistsException("Couldn't create doctor. A user with that email already exists");
        }

        Specialty specialty = specialtyRepository.findByName(createDoctorDto.getSpecialtyName())
                .orElseThrow(() -> new EntityNotFoundException("Couldn't create doctor. Specialty not found."));

        Role role = roleRepository.findByName(ERole.ROLE_DOCTOR)
                .orElseThrow(() -> new EntityNotFoundException("Error: Role is not found."));

        Doctor doctor = Doctor.builder()
                .firstName(createDoctorDto.getFirstName())
                .lastName(createDoctorDto.getLastName())
                .username(createDoctorDto.getUsername())
                .email(createDoctorDto.getEmail())
                .password(encoder.encode(createDoctorDto.getPassword()))
                .specialty(specialty)
                .roles(Set.of(role))
                .build();

        return doctorRepository.save(doctor);
    }

    @Override
    public CreateDoctorDto getDoctor(String username) {
        Doctor doctor = doctorRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("Doctor not found"));

        return doctor.toDto();
    }

    @Override
    public List<CreateDoctorDto> getDoctors() {
        List<Doctor> doctors = doctorRepository.findAll();
        return doctors.stream().map(Doctor::toDto).toList();
    }

    @Override
    public Doctor updateDoctor(String username, DoctorDto doctorDto) {
        Specialty specialty = specialtyRepository.findByName(doctorDto.getSpecialtyName())
                .orElseThrow(() -> new EntityNotFoundException("Couldn't update doctor. Specialty not found."));
        Doctor doctor = doctorRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("Doctor not found"));

        doctor.setFirstName(doctorDto.getFirstName());
        doctor.setLastName(doctorDto.getLastName());
        doctor.setEmail(doctorDto.getEmail());
        doctor.setSpecialty(specialty);
        return doctorRepository.save(doctor);
    }

    @Override
    public void deleteDoctor(String doctorUsername) {
        Doctor doctor = doctorRepository.findByUsername(doctorUsername)
                .orElseThrow(() -> new EntityNotFoundException("Doctor not found"));
        doctorRepository.delete(doctor);
    }
}
