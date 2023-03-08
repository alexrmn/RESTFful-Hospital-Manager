package ro.alexrmn.hospitalmanagerbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.alexrmn.hospitalmanagerbackend.model.Doctor;

import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {


    Optional<Doctor> findByUsername(String username);

}
