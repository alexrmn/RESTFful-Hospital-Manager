package ro.alexrmn.hospitalmanagerbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.alexrmn.hospitalmanagerbackend.model.Appointment;
import ro.alexrmn.hospitalmanagerbackend.model.Specialty;
import ro.alexrmn.hospitalmanagerbackend.model.dto.CreateSpecialtyDto;
import ro.alexrmn.hospitalmanagerbackend.model.dto.SpecialtyDto;
import ro.alexrmn.hospitalmanagerbackend.repository.SpecialtyRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpecialtyServiceImpl implements SpecialtyService {

    private final SpecialtyRepository specialtyRepository;
    @Override
    public List<SpecialtyDto> getSpecialties() {
        List<Specialty> specialties = specialtyRepository.findAll();
        return specialties.stream().map(Specialty::toDto).toList();
    }

    @Override
    public Specialty save(CreateSpecialtyDto createSpecialtyDto) {
        Specialty specialty = Specialty.builder()
                .name(createSpecialtyDto.getName())
                .build();
        return specialtyRepository.save(specialty);
    }
}
