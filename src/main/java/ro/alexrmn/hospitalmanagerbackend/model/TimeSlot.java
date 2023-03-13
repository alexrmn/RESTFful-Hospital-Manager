package ro.alexrmn.hospitalmanagerbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@Entity
@Table(name = "TimeSlots")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class TimeSlot extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private TimeSlot name;


}
