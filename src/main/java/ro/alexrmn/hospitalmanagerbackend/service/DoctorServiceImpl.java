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
import ro.alexrmn.hospitalmanagerbackend.repository.UserRepository;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService{

    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;
    private final SpecialtyRepository specialtyRepository;
    private final PasswordEncoder encoder;
    private final RoleRepository roleRepository;

    @Override
    public DoctorDto saveDoctor(CreateDoctorDto createDoctorDto) {
        if(userRepository.existsByUsername(createDoctorDto.getUsername())) {
            throw new EntityExistsException("Couldn't create doctor. A user with that username already exists");
        }
        if(userRepository.existsByEmail(createDoctorDto.getEmail())) {
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

        return doctorRepository.save(doctor).toDto();
    }

    @Override
    public DoctorDto getDoctor(Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Doctor not found"));

        return doctor.toDto();
    }

    @Override
    public List<DoctorDto> getDoctors() {
        List<Doctor> doctors = doctorRepository.findAll();
        return doctors.stream().map(Doctor::toDto).toList();
    }

    @Override
    public DoctorDto updateDoctor(Long id, DoctorDto doctorDto) {
        Specialty specialty = specialtyRepository.findByName(doctorDto.getSpecialtyName())
                .orElseThrow(() -> new EntityNotFoundException("Couldn't update doctor. Specialty not found."));
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Doctor not found"));

        doctor.setFirstName(doctorDto.getFirstName());
        doctor.setLastName(doctorDto.getLastName());
        doctor.setEmail(doctorDto.getEmail());
        doctor.setSpecialty(specialty);
        return doctorRepository.save(doctor).toDto();
    }

    @Override
    public void deleteDoctor(Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Doctor not found"));
        doctorRepository.delete(doctor);
    }

    @Override
    public List<DoctorDto> findBySpecialty(Long specialtyId) {
        Specialty specialty = specialtyRepository.findById(specialtyId)
                .orElseThrow(() -> new EntityNotFoundException("Specialty not found"));
        List<Doctor> doctors = doctorRepository.findBySpecialty(specialty);
        return doctors.stream().map(Doctor::toDto).toList();
    }
}
