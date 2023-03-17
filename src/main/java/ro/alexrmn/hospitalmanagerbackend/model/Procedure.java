package ro.alexrmn.hospitalmanagerbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ro.alexrmn.hospitalmanagerbackend.model.dto.DiagnosisDto;
import ro.alexrmn.hospitalmanagerbackend.model.dto.ProcedureDto;

import java.util.List;

@Data
@Entity
@Table(name = "procedures")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Procedure extends NamedResource {

    @ManyToMany(mappedBy = "procedures")
    @JsonIgnore
    private List<Appointment> appointments;

    public ProcedureDto toDto() {
        return ProcedureDto.builder()
                .id(this.getId())
                .name(this.getName())
                .build();
    }
}
