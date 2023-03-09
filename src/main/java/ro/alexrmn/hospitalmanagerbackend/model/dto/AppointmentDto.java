package ro.alexrmn.hospitalmanagerbackend.model.dto;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import ro.alexrmn.hospitalmanagerbackend.model.*;

import java.time.LocalDate;
import java.util.Set;

@Data
@SuperBuilder
public class AppointmentDto extends BaseEntity{

    private LocalDate date;

    private PatientDto patient;

    private DoctorDto doctor;

    private Specialty specialty;

    private Set<Diagnosis> diagnoses;

    private Set<Procedure> procedures;

    private Set<Medication> medications;

    private String summary;
}
