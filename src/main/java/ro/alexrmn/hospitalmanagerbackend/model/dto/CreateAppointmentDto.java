package ro.alexrmn.hospitalmanagerbackend.model.dto;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.alexrmn.hospitalmanagerbackend.model.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateAppointmentDto {

    @NotNull
    private LocalDate date;

    @NotNull
    private TimeSlot timeSlot;

    @NotNull
    private Patient patient;

    @NotNull
    private Doctor doctor;

    @NotNull
    private Specialty specialty;

}
