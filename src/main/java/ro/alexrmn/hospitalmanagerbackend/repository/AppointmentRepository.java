package ro.alexrmn.hospitalmanagerbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.alexrmn.hospitalmanagerbackend.model.*;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findBySpecialty(Specialty specialty);

    Boolean existsByDateAndTimeSlotAndDoctor(LocalDate date, TimeSlot timeSlot, Doctor doctor);

}
