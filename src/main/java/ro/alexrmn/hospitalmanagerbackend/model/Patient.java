package ro.alexrmn.hospitalmanagerbackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ro.alexrmn.hospitalmanagerbackend.model.dto.CreatePatientDto;

import java.util.List;

@Entity
@Table(name = "patients")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Patient extends User{

    private String phoneNumber;

    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private List<Appointment> appointments;

    public CreatePatientDto toDto(){
        return CreatePatientDto.builder()
                .firstName(this.getFirstName())
                .lastName(this.getLastName())
                .phoneNumber(this.phoneNumber)
                .appointments(this.appointments)
                .build();

    }
}
