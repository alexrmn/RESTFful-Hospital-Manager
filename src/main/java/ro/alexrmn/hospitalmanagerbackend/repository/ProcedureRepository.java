package ro.alexrmn.hospitalmanagerbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.alexrmn.hospitalmanagerbackend.model.Procedure;

public interface ProcedureRepository extends JpaRepository<Procedure, Long> {
    boolean existsByName(String name);
}
