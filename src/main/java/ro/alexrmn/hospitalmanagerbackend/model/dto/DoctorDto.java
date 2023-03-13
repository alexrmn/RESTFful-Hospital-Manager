package ro.alexrmn.hospitalmanagerbackend.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ro.alexrmn.hospitalmanagerbackend.model.Specialty;


@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDto extends UserDto {

    private String specialtyName;
}
