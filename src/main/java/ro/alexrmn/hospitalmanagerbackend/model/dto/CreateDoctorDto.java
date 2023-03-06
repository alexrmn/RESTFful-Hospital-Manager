package ro.alexrmn.hospitalmanagerbackend.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CreateDoctorDto extends CreateUserDto {

    @NotBlank
    private String specialtyName;


}
