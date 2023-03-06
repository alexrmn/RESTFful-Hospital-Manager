package ro.alexrmn.hospitalmanagerbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.alexrmn.hospitalmanagerbackend.model.Patient;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, String> {

    Optional<Patient> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
