package ro.alexrmn.hospitalmanagerbackend.service;

import ro.alexrmn.hospitalmanagerbackend.model.Doctor;
import ro.alexrmn.hospitalmanagerbackend.model.Specialty;
import ro.alexrmn.hospitalmanagerbackend.model.dto.CreateDoctorDto;
import ro.alexrmn.hospitalmanagerbackend.model.dto.DoctorDto;

import java.util.List;

public interface DoctorService {

    DoctorDto saveDoctor(CreateDoctorDto createDoctorDto);

    DoctorDto getDoctor(Long id);

    List<DoctorDto> getDoctors();

    DoctorDto updateDoctor(Long id, DoctorDto doctorDto);

    void deleteDoctor(Long id);

    List<DoctorDto> findBySpecialty(Long specialtyId);
}
