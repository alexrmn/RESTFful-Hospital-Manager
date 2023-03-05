package ro.alexrmn.hospitalmanagerbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.alexrmn.hospitalmanagerbackend.model.Specialty;

import java.util.Optional;

public interface SpecialtyRepository extends JpaRepository <Specialty, Long> {

    Optional<Specialty> findByName(String name);
}
