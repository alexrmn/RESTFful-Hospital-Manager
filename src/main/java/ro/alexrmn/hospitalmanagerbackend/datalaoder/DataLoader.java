package ro.alexrmn.hospitalmanagerbackend.datalaoder;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ro.alexrmn.hospitalmanagerbackend.model.*;
import ro.alexrmn.hospitalmanagerbackend.model.dto.CreateAdminDto;
import ro.alexrmn.hospitalmanagerbackend.model.dto.CreateAppointmentDto;
import ro.alexrmn.hospitalmanagerbackend.model.dto.CreateDoctorDto;
import ro.alexrmn.hospitalmanagerbackend.model.dto.CreatePatientDto;
import ro.alexrmn.hospitalmanagerbackend.repository.*;
import ro.alexrmn.hospitalmanagerbackend.service.AdminService;
import ro.alexrmn.hospitalmanagerbackend.service.AppointmentService;
import ro.alexrmn.hospitalmanagerbackend.service.DoctorService;
import ro.alexrmn.hospitalmanagerbackend.service.PatientService;

import java.time.LocalDate;

//@Component
@RequiredArgsConstructor
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final SpecialtyRepository specialtyRepository;

    private final DoctorService doctorService;
    private final PatientService patientService;
    private final AdminService adminService;
    private final AppointmentService appointmentService;
    private final RoleRepository roleRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        //creating roles
//        Role patient = Role.builder()
//                .id(1L)
//                .name(ERole.ROLE_PATIENT)
//                .build();
//        Role doctor = Role.builder()
//                .id(1L)
//                .name(ERole.ROLE_DOCTOR)
//                .build();
//        Role admin = Role.builder()
//                .id(1L)
//                .name(ERole.ROLE_ADMIN)
//                .build();
//        roleRepository.save(patient);
//        roleRepository.save(doctor);
//        roleRepository.save(admin);


        //creating specialties
        Specialty cardiology = Specialty.builder()
                .name("Cardiology")
                .build();
        specialtyRepository.save(cardiology);

        //creating doctors
        CreateDoctorDto createDoctorDto1 = CreateDoctorDto.builder()
                .firstName("Greg")
                .lastName("House")
                .username("doctor1")
                .email("doctor1@mail.com")
                .password("doctor1")
                .specialtyName("Cardiology")
                .build();
//        Doctor doctor1 = doctorService.saveDoctor(createDoctorDto1);


        //creating patients
        CreatePatientDto createPatientDto1 = CreatePatientDto.builder()
                .firstName("Will")
                .lastName("Smith")
                .username("patient1")
                .email("patient1@mail.com")
                .password("patient1")
                .build();
//        Patient patient1 = patientService.savePatient(createPatientDto1);

        //creating admins
        CreateAdminDto createAdminDto1 = CreateAdminDto.builder()
                .firstName("Admin")
                .lastName("Admin")
                .username("admin1")
                .email("admin1@mail.com")
                .password("admin1")
                .build();
        adminService.saveAdmin(createAdminDto1);

        //creatingAppointments


    }
}
