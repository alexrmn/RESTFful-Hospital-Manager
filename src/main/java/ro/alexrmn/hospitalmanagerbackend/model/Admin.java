package ro.alexrmn.hospitalmanagerbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@Entity
@Table(name = "admins")
@SuperBuilder
@NoArgsConstructor
//@AllArgsConstructor
public class Admin extends User {

}
