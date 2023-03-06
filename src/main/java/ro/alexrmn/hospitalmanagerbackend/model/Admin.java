package ro.alexrmn.hospitalmanagerbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ro.alexrmn.hospitalmanagerbackend.model.dto.AdminDto;

@Data
@Entity
@Table(name = "admins")
@SuperBuilder
@NoArgsConstructor
//@AllArgsConstructor
public class Admin extends User {

    public AdminDto toDto() {
        return AdminDto.builder()
                .firstName(this.getFirstName())
                .lastName(this.getLastName())
                .email(this.getEmail())
                .build();
    }

}
