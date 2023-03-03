package ro.alexrmn.hospitalmanagerbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.alexrmn.hospitalmanagerbackend.model.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
