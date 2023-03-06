package ro.alexrmn.hospitalmanagerbackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ro.alexrmn.hospitalmanagerbackend.model.dto.CreateDoctorDto;
import ro.alexrmn.hospitalmanagerbackend.model.dto.DoctorDto;

import java.util.List;


@Data
@Entity
@Table(name = "doctors")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Doctor extends User{

    @ManyToOne
    private Specialty specialty;

    @OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private List<Appointment> appointments;

    public DoctorDto toDto(){
        return DoctorDto.builder()
                .firstName(this.getFirstName())
                .lastName(this.getLastName())
                .email(this.getEmail())
                .specialtyName(specialty.getName())
                .build();

    }

//    public DoctorDto toDetailedDto(){
//        return DoctorDto.builder()
//                .firstName(this.getFirstName())
//                .lastName(this.getLastName())
//                .email(this.getEmail())
//                .specialtyName(specialty.getName())
//                .username(this.getUsername())
//                .build();
//
//    }
}
