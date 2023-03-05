package ro.alexrmn.hospitalmanagerbackend.service;

import ro.alexrmn.hospitalmanagerbackend.model.Doctor;
import ro.alexrmn.hospitalmanagerbackend.model.dto.CreateDoctorDto;

public interface DoctorService {

    Doctor saveDoctor(CreateDoctorDto createDoctorDto);
}
