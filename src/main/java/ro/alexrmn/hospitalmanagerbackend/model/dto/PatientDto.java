package ro.alexrmn.hospitalmanagerbackend.model.dto;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ro.alexrmn.hospitalmanagerbackend.model.Appointment;

import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PatientDto extends UserDto {

    @Pattern(regexp="0[0-9]{9}")
    private String phoneNumber;

}
