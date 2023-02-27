package ro.alexrmn.hospitalmanagerbackend.model;

import jakarta.persistence.*;
import lombok.Data;



@Data
@Entity
@Table(name = "doctors")
public class Doctor extends User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
}
