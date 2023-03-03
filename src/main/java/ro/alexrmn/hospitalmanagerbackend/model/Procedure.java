package ro.alexrmn.hospitalmanagerbackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@Entity
@Table(name = "procedures")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Procedure extends NamedResource {

    @ManyToMany(mappedBy = "procedures")
    private List<Appointment> appointments;
}
