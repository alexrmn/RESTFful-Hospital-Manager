package ro.alexrmn.hospitalmanagerbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
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

    @Column(length = 2000)
    private String description;

    private String imageLink;

    @OneToMany(mappedBy = "specialty")
    @JsonIgnore
    private List<Doctor> doctors;

    public SpecialtyDto toDto() {
        return SpecialtyDto.builder()
                .id(this.getId())
                .name(this.getName())
                .description(description)
                .imageLink(imageLink)
                .build();
    }

    @Override
    public String toString() {
        return this.getName();
    }

}
