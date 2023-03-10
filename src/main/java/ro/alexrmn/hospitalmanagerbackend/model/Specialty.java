package ro.alexrmn.hospitalmanagerbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ro.alexrmn.hospitalmanagerbackend.model.dto.SpecialtyDto;


import java.util.List;

@Data
@Entity
@Table(name = "specialties")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Specialty extends NamedResource {

    @OneToMany(mappedBy = "specialty")
    @JsonIgnore
    private List<Doctor> doctors;

    public SpecialtyDto toDto() {
        return SpecialtyDto.builder()
                .id(this.getId())
                .name(this.getName())
                .build();
    }

}
