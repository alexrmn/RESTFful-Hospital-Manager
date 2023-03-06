package ro.alexrmn.hospitalmanagerbackend.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ro.alexrmn.hospitalmanagerbackend.model.Doctor;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDto extends UserDto {

    @NotBlank
    private String specialtyName;


}
