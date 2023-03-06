package ro.alexrmn.hospitalmanagerbackend.service;

import ro.alexrmn.hospitalmanagerbackend.model.Doctor;
import ro.alexrmn.hospitalmanagerbackend.model.dto.CreateDoctorDto;
import ro.alexrmn.hospitalmanagerbackend.model.dto.DoctorDto;

import java.util.List;

public interface DoctorService {

    Doctor saveDoctor(CreateDoctorDto createDoctorDto);

    CreateDoctorDto getDoctor(String username);

    List<CreateDoctorDto> getDoctors();

    Doctor updateDoctor(String username, DoctorDto doctorDto);

    void deleteDoctor(String doctorUsername);
}
