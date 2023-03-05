package ro.alexrmn.hospitalmanagerbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.alexrmn.hospitalmanagerbackend.model.ERole;
import ro.alexrmn.hospitalmanagerbackend.model.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(ERole name);
}
