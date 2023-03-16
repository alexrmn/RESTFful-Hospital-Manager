package ro.alexrmn.hospitalmanagerbackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ro.alexrmn.hospitalmanagerbackend.model.dto.DiagnosisDto;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "diagnoses")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Diagnosis extends  NamedResource{

    @ManyToMany(mappedBy = "diagnoses")
    private List<Appointment> appointments;

    public DiagnosisDto toDto() {
        return DiagnosisDto.builder()
                .id(this.getId())
                .name(this.getName())
                .build();
    }
    public void addAppointment(Appointment appointment){
        if (this.appointments == null) {
            this.appointments = new ArrayList<>();
        }
        this.appointments.add(appointment);
    }
}
