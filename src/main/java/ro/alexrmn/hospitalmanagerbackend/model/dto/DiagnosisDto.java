package ro.alexrmn.hospitalmanagerbackend.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ro.alexrmn.hospitalmanagerbackend.model.BaseEntity;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class DiagnosisDto extends BaseEntity {

    @NotBlank(message ="Name must not be blank")
    private String name;
}
