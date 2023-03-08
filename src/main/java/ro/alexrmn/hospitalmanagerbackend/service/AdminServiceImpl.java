package ro.alexrmn.hospitalmanagerbackend.service;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ro.alexrmn.hospitalmanagerbackend.model.Admin;
import ro.alexrmn.hospitalmanagerbackend.model.ERole;
import ro.alexrmn.hospitalmanagerbackend.model.Role;
import ro.alexrmn.hospitalmanagerbackend.model.dto.AdminDto;
import ro.alexrmn.hospitalmanagerbackend.model.dto.CreateAdminDto;
import ro.alexrmn.hospitalmanagerbackend.repository.AdminRepository;
import ro.alexrmn.hospitalmanagerbackend.repository.RoleRepository;
import ro.alexrmn.hospitalmanagerbackend.repository.UserRepository;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final RoleRepository roleRepository;
    @Override
    public Admin saveAdmin(CreateAdminDto createAdminDto) {
        if(userRepository.existsByUsername(createAdminDto.getUsername())) {
            throw new EntityExistsException("Couldn't create admin. A user with that username already exists");
        }
        if(userRepository.existsByEmail(createAdminDto.getEmail())) {
            throw new EntityExistsException("Couldn't create admin. A user with that email already exists");
        }
        Role role = roleRepository.findByName(ERole.ROLE_ADMIN)
                .orElseThrow(() -> new EntityNotFoundException("Error: Role is not found."));
        Admin admin = Admin.builder()
                .firstName(createAdminDto.getFirstName())
                .lastName(createAdminDto.getLastName())
                .username(createAdminDto.getUsername())
                .email(createAdminDto.getEmail())
                .password(encoder.encode(createAdminDto.getPassword()))
                .roles(Set.of(role))
                .build();

        return adminRepository.save(admin);
    }

    @Override
    public AdminDto getAdmin(Long id) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Admin not found"));

        return admin.toDto();
    }

    @Override
    public List<AdminDto> getAdmins() {
        List<Admin> admins = adminRepository.findAll();
        return admins.stream().map(Admin::toDto).toList();
    }

    @Override
    public Admin updateAdmin(Long id, AdminDto adminDto) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Admin not found"));
        admin.setFirstName(adminDto.getFirstName());
        admin.setLastName(adminDto.getLastName());
        admin.setEmail(adminDto.getEmail());
        return adminRepository.save(admin);
    }

    @Override
    public void deleteAdmin(Long id) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Admin not found"));
        adminRepository.delete(admin);

    }
}
