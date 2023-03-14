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



    @NotNull(message = "Date must not be null")
    private LocalDate date;

    @NotNull(message = "timeslotId must not be null")
    private Long timeslotId;

    @NotNull(message = "patientId must not be null")
    private Long patientId;

    @NotNull(message = "doctorId must not be null")
    private Long doctorId;

    @NotNull(message = "specialtyId must not be null")
    private Long specialtyId;

}
