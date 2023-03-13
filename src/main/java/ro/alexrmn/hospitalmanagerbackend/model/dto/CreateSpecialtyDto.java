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
public class CreateSpecialtyDto {

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    private String imageLink;
}
