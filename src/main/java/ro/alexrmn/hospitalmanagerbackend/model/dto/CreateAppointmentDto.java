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
    TimeSlot timeSlot;

    @NotNull
    private Long patientId;

    @NotNull
    private Long doctorId;

    @NotNull
    private Long specialtyId;

}
