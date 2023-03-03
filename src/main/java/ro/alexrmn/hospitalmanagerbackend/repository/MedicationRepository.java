package ro.alexrmn.hospitalmanagerbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.alexrmn.hospitalmanagerbackend.model.Medication;

public interface MedicationRepository extends JpaRepository<Medication, Long> {
}
