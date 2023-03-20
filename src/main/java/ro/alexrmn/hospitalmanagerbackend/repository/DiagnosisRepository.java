package ro.alexrmn.hospitalmanagerbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.alexrmn.hospitalmanagerbackend.model.Diagnosis;

public interface DiagnosisRepository extends JpaRepository<Diagnosis, Long> {

    boolean existsByName(String name);
}
