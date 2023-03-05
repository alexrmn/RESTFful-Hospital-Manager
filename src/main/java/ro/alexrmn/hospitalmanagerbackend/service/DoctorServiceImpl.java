package ro.alexrmn.hospitalmanagerbackend.service;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ro.alexrmn.hospitalmanagerbackend.model.Doctor;
import ro.alexrmn.hospitalmanagerbackend.model.Specialty;
import ro.alexrmn.hospitalmanagerbackend.model.dto.CreateDoctorDto;
import ro.alexrmn.hospitalmanagerbackend.repository.DoctorRepository;
import ro.alexrmn.hospitalmanagerbackend.repository.SpecialtyRepository;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService{

    private final DoctorRepository doctorRepository;
    private final SpecialtyRepository specialtyRepository;
    private final PasswordEncoder encoder;

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

        Doctor doctor = Doctor.builder()
                .firstName(createDoctorDto.getFirstName())
                .lastName(createDoctorDto.getLastName())
                .username(createDoctorDto.getUsername())
                .email(createDoctorDto.getEmail())
                .password(encoder.encode(createDoctorDto.getPassword()))
                .specialty(specialty)
                .build();

        return doctorRepository.save(doctor);
    }
}
