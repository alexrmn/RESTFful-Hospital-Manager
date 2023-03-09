package ro.alexrmn.hospitalmanagerbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import ro.alexrmn.hospitalmanagerbackend.model.dto.CreatePatientDto;
import ro.alexrmn.hospitalmanagerbackend.model.dto.PatientDto;

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
    @JsonIgnore
    private List<Appointment> appointments;

    public PatientDto toDto(){
        return PatientDto.builder()
                .id(this.getId())
                .firstName(this.getFirstName())
                .lastName(this.getLastName())
                .phoneNumber(this.getPhoneNumber())
                .email(this.getEmail())
                .build();

    }

    @Override
    public String toString() {
        return getFirstName() + " " + getLastName();
    }
}
