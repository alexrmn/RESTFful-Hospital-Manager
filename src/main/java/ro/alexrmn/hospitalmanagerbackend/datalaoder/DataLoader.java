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
import ro.alexrmn.hospitalmanagerbackend.repository.AdminRepository;
import ro.alexrmn.hospitalmanagerbackend.repository.DoctorRepository;
import ro.alexrmn.hospitalmanagerbackend.repository.PatientRepository;
import ro.alexrmn.hospitalmanagerbackend.repository.SpecialtyRepository;
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

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        //creating specialties
//        Specialty cardiology = Specialty.builder()
//                .name("Cardiology")
//                .build();
//        specialtyRepository.save(cardiology);

        //creating doctors
        CreateDoctorDto createDoctorDto1 = CreateDoctorDto.builder()
                .firstName("Greg")
                .lastName("House")
                .username("doctor1")
                .email("doctor1@mail.com")
                .password("doctor1")
                .specialtyName("Cardiology")
                .build();
        Doctor doctor1 = doctorService.saveDoctor(createDoctorDto1);


        //creating patients
        CreatePatientDto createPatientDto1 = CreatePatientDto.builder()
                .firstName("Will")
                .lastName("Smith")
                .username("patient1")
                .email("patient1@mail.com")
                .password("patient1")
                .build();
        Patient patient1 = patientService.savePatient(createPatientDto1);

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
        CreateAppointmentDto createAppointmentDto1 = CreateAppointmentDto.builder()
                .date(LocalDate.now())
                .timeSlot(TimeSlot.TIME_SLOT_1)
                .doctor(doctor1)
                .patient(patient1)
                .specialty(specialtyRepository.findByName("Cardiology").get())
                .build();
        appointmentService.saveAppointment(createAppointmentDto1);

    }
}
