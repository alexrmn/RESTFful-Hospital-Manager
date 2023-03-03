package ro.alexrmn.hospitalmanagerbackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@Entity
@Table(name = "medications")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Medication extends NamedResource {

    @ManyToMany(mappedBy = "medications")
    private List<Appointment> appointments;
}
