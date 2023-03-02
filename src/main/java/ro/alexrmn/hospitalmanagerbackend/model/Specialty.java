package ro.alexrmn.hospitalmanagerbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@Entity
@Table(name = "specialties")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Specialty extends BaseEntity{

    @OneToMany(mappedBy = "specialty")
    @JsonIgnore
    private List<Doctor> doctors;

}
