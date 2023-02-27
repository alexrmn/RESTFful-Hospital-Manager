package ro.alexrmn.hospitalmanagerbackend.model;

import jakarta.persistence.*;
import lombok.Data;



@Data
@Entity
@Table(name = "doctors")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
}
