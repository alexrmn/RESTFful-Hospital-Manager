package ro.alexrmn.hospitalmanagerbackend.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpecialtyDto {

    @NotNull
    private Long id;

    @NotBlank
    private String name;
}
