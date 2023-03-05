package ro.alexrmn.hospitalmanagerbackend.datalaoder;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ro.alexrmn.hospitalmanagerbackend.model.Admin;
import ro.alexrmn.hospitalmanagerbackend.model.Doctor;
import ro.alexrmn.hospitalmanagerbackend.model.Patient;
import ro.alexrmn.hospitalmanagerbackend.model.Specialty;
import ro.alexrmn.hospitalmanagerbackend.repository.AdminRepository;
import ro.alexrmn.hospitalmanagerbackend.repository.DoctorRepository;
import ro.alexrmn.hospitalmanagerbackend.repository.PatientRepository;
import ro.alexrmn.hospitalmanagerbackend.repository.SpecialtyRepository;

//@Component
@RequiredArgsConstructor
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final SpecialtyRepository specialtyRepository;
    private final AdminRepository adminRepository;
    private final PasswordEncoder encoder;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
//        //creating specialties
//        Specialty cardiology = Specialty.builder()
//                .name("Cardiology")
//                .build();
//        specialtyRepository.save(cardiology);
//
//        //creating doctors
//        Doctor doctor1 = Doctor.builder()
//                .email("doctor1@mail.com")
//                .password(encoder.encode("doctor1"))
//                .roles("ROLE_DOCTOR")
//                .firstName("Gregory")
//                .lastName("House")
//                .specialty(cardiology)
//                .build();
//        doctorRepository.save(doctor1);
//
//        //creating patients
//        Patient patient1 = Patient.builder()
//                .email("patient1@mail.com")
//                .password(encoder.encode("patient1"))
//                .roles("ROLE_PATIENT")
//                .firstName("Will")
//                .lastName("Smith")
//                .build();
//
//        patientRepository.save(patient1);
//
//        //creating admins
//        Admin admin1 = Admin.builder()
//                .email("admin1@mail.com")
//                .password(encoder.encode("admin1"))
//                .roles("ROLE_ADMIN")
//                .firstName("Morgan")
//                .lastName("Freeman")
//                .build();
//
//        adminRepository.save(admin1);
    }
}
