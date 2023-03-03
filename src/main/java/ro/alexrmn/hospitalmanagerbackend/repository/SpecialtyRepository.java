package ro.alexrmn.hospitalmanagerbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.alexrmn.hospitalmanagerbackend.model.Specialty;

public interface SpecialtyRepository extends JpaRepository <Specialty, Long> {
}
