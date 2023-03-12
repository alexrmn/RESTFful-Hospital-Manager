package ro.alexrmn.hospitalmanagerbackend.service;


import ro.alexrmn.hospitalmanagerbackend.model.Specialty;
import ro.alexrmn.hospitalmanagerbackend.model.dto.CreateSpecialtyDto;
import ro.alexrmn.hospitalmanagerbackend.model.dto.SpecialtyDto;

import java.util.List;

public interface SpecialtyService {
    List<SpecialtyDto> getSpecialties();

    Specialty save(SpecialtyDto specialtyDto);

    void delete(Long specialtyId);

    SpecialtyDto getSpecialty(Long specialtyId);

    Specialty update(Long id, SpecialtyDto specialtyDto);
}
