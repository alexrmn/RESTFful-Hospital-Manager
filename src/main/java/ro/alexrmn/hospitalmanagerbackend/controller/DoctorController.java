package ro.alexrmn.hospitalmanagerbackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.alexrmn.hospitalmanagerbackend.model.Doctor;
import ro.alexrmn.hospitalmanagerbackend.repository.DoctorRepository;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/doctors")
@CrossOrigin
public class DoctorController {

    private final DoctorRepository doctorRepository;

    @GetMapping()
    public List<Doctor> getDoctors() {
        return  doctorRepository.findAll();
    }

    @PostMapping
    public Doctor createNewDoctor(@RequestBody Doctor doctor){
        return doctorRepository.save(doctor);
    }


}
