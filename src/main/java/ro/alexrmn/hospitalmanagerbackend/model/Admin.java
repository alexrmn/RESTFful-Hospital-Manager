package ro.alexrmn.hospitalmanagerbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ro.alexrmn.hospitalmanagerbackend.model.dto.AdminDto;

@Data
@Entity
@Table(name = "admins")
@SuperBuilder
@NoArgsConstructor
public class Admin extends User {

    public AdminDto toDto() {
        return AdminDto.builder()
                .id(this.getId())
                .firstName(this.getFirstName())
                .lastName(this.getLastName())
                .email(this.getEmail())
                .build();
    }

}
