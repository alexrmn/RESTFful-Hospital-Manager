package ro.alexrmn.hospitalmanagerbackend.service;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.alexrmn.hospitalmanagerbackend.model.Specialty;
import ro.alexrmn.hospitalmanagerbackend.model.dto.CreateSpecialtyDto;
import ro.alexrmn.hospitalmanagerbackend.model.dto.SpecialtyDto;
import ro.alexrmn.hospitalmanagerbackend.repository.AppointmentRepository;
import ro.alexrmn.hospitalmanagerbackend.repository.DoctorRepository;
import ro.alexrmn.hospitalmanagerbackend.repository.SpecialtyRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpecialtyServiceImpl implements SpecialtyService {

    private final SpecialtyRepository specialtyRepository;
    private final DoctorRepository doctorRepository;
    private final AppointmentRepository appointmentRepository;

    @Override
    public List<SpecialtyDto> getSpecialties() {
        List<Specialty> specialties = specialtyRepository.findAll();
        return specialties.stream().map(Specialty::toDto).toList();
    }

    @Override
    public Specialty save(SpecialtyDto specialtyDto) {
        if (specialtyRepository.existsByName(specialtyDto.getName())) {
            throw new EntityExistsException("Specialty already exists");
        }
        Specialty specialty = Specialty.builder()
                .name(specialtyDto.getName())
                .description(specialtyDto.getDescription())
                .build();
        return specialtyRepository.save(specialty);
    }

    @Override
    public void delete(Long specialtyId) {
        Specialty specialty = specialtyRepository.findById(specialtyId)
                .orElseThrow(() -> new EntityNotFoundException("Specialty not found"));
        doctorRepository.findBySpecialty(specialty)
                .forEach((doctor -> {
                    doctor.setSpecialty(null);
                }));
        appointmentRepository.findBySpecialty(specialty)
                .forEach((appointment -> {
                    appointment.setSpecialty(null);
                }));
        specialtyRepository.delete(specialty);
    }

    @Override
    public SpecialtyDto getSpecialty(Long specialtyId) {
        Specialty specialty = specialtyRepository.findById(specialtyId)
                .orElseThrow(() -> new EntityNotFoundException("Specialty not found"));
        return specialty.toDto();
    }

    @Override
    public Specialty update(Long id, SpecialtyDto specialtyDto) {
        Specialty specialty = specialtyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Specialty not found"));
        specialty.setName(specialtyDto.getName());
        specialty.setDescription(specialtyDto.getDescription());
        return specialtyRepository.save(specialty);
    }
}
