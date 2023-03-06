package ro.alexrmn.hospitalmanagerbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.alexrmn.hospitalmanagerbackend.model.Admin;
import ro.alexrmn.hospitalmanagerbackend.model.Doctor;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, String> {

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    Optional<Admin> findByUsername(String username);
}
