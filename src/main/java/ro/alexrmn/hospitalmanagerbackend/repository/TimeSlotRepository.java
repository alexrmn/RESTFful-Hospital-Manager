package ro.alexrmn.hospitalmanagerbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.alexrmn.hospitalmanagerbackend.model.ETimeSlot;
import ro.alexrmn.hospitalmanagerbackend.model.TimeSlot;

import java.util.List;
import java.util.Optional;

public interface TimeSlotRepository extends JpaRepository<TimeSlot, Long> {
    Optional<TimeSlot> findByValue(ETimeSlot value);

}
