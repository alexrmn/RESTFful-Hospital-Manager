package ro.alexrmn.hospitalmanagerbackend.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpecialtyDto {


    private Long id;

    @NotBlank(message ="Name must not be blank")
    private String name;

    @NotBlank(message ="Description must not be blank")
    private String description;
}
