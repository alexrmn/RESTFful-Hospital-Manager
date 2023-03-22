package ro.alexrmn.hospitalmanagerbackend.service;


import ro.alexrmn.hospitalmanagerbackend.model.Specialty;
import ro.alexrmn.hospitalmanagerbackend.model.dto.SpecialtyDto;

import java.util.List;

public interface SpecialtyService {
    List<SpecialtyDto> getSpecialties();

    SpecialtyDto save(SpecialtyDto specialtyDto);

    void delete(Long specialtyId);

    SpecialtyDto getSpecialty(Long specialtyId);

    SpecialtyDto update(Long id, SpecialtyDto specialtyDto);
}
