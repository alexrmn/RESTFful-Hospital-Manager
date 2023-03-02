package ro.alexrmn.hospitalmanagerbackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "diagnoses")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Diagnosis {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "diagnoses")
    private List<Appointment> appointments;

    public void addAppointment(Appointment appointment){
        if (this.appointments == null) {
            this.appointments = new ArrayList<>();
        }
        this.appointments.add(appointment);
    }
}
