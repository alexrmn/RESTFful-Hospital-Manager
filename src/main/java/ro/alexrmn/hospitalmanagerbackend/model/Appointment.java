package ro.alexrmn.hospitalmanagerbackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "appointments")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {

    @Id
    @GeneratedValue
    private Long id;

    private LocalDateTime date;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "patient_email", nullable = false)
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_email", nullable = false)
    private Doctor doctor;

    @ManyToOne
    private Specialty specialty;

    @ManyToMany()
    @JoinTable(name = "appointment_diagnosis",
            joinColumns = @JoinColumn(name = "appointment_id"),
            inverseJoinColumns = @JoinColumn(name = "diagnosis_id"))
    private Set<Diagnosis> diagnoses;

    @ManyToMany
    @JoinTable(
            name = "appointment_procedure",
            joinColumns = @JoinColumn(name = "appointment_id"),
            inverseJoinColumns = @JoinColumn(name = "procedure_id")
    )
    private Set<Procedure> procedures;

    @ManyToMany
    @JoinTable(
            name = "appointment_medication",
            joinColumns = @JoinColumn(name = "appointment_id"),
            inverseJoinColumns = @JoinColumn(name = "medication_id")
    )
    private Set<Medication> medications;

    @Column(length = 2000)
    private String summary;

    public void addDiagnosis(Diagnosis diagnosis) {
        if (this.diagnoses == null) {
            this.diagnoses = new HashSet<>();
        }
        this.diagnoses.add(diagnosis);
    }

    public void removeDiagnosis(Diagnosis diagnosis){
        this.diagnoses.remove(diagnosis);
        diagnosis.getAppointments().remove(this);
    }

    public void addProcedure(Procedure procedure) {
        if (this.procedures == null) {
            this.procedures = new HashSet<>();
        }
        this.procedures.add(procedure);
    }

    public void removeProcedure(Procedure procedure) {
        this.procedures.remove(procedure);
        procedure.getAppointments().remove(this);
    }

    public void addMedication(Medication medication){
        if (this.medications == null) {
            this.medications = new HashSet<>();
        }
        this.medications.add(medication);
    }

    public void removeMedication(Medication medication) {
        this.medications.remove(medication);
        medication.getAppointments().remove(this);
    }



}
