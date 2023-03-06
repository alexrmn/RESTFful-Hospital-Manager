package ro.alexrmn.hospitalmanagerbackend.service;

import ro.alexrmn.hospitalmanagerbackend.model.Doctor;
import ro.alexrmn.hospitalmanagerbackend.model.dto.DoctorDto;

import java.util.List;

public interface DoctorService {

    Doctor saveDoctor(DoctorDto doctorDto);

    DoctorDto getDoctor(String username);

    List<DoctorDto> getDoctors();

    Doctor updateDoctor(DoctorDto doctorDto);

    void deleteDoctor(String doctorUsername);
}
