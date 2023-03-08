package ro.alexrmn.hospitalmanagerbackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ro.alexrmn.hospitalmanagerbackend.Validators.ObjectValidator;
import ro.alexrmn.hospitalmanagerbackend.model.Admin;
import ro.alexrmn.hospitalmanagerbackend.model.dto.AdminDto;
import ro.alexrmn.hospitalmanagerbackend.model.dto.CreateAdminDto;
import ro.alexrmn.hospitalmanagerbackend.service.AdminService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admins")
@CrossOrigin
public class AdminController {

    private final AdminService adminService;
    private final ObjectValidator<CreateAdminDto> createAdminValidator;
    private final ObjectValidator<AdminDto> editAdminValidator;

    @GetMapping("/{adminId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AdminDto> getAdmin(@PathVariable Long adminId) {
        AdminDto adminDto = adminService.getAdmin(adminId);
        return ResponseEntity.ok().body(adminDto);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<AdminDto>> getAdmins() {
        List<AdminDto> adminDtoList = adminService.getAdmins();
        return ResponseEntity.ok().body(adminDtoList);
    }


    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Admin> createAdmin(@RequestBody CreateAdminDto createAdminDto){
        createAdminValidator.validate(createAdminDto);
        Admin admin = adminService.saveAdmin(createAdminDto);
        return ResponseEntity.accepted().body(admin);
    }

    @PutMapping("/{adminId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Admin> updateAdmin(@RequestBody AdminDto adminDto,
                                             @PathVariable Long adminId) {
        editAdminValidator.validate(adminDto);
        Admin admin = adminService.updateAdmin(adminId, adminDto);
        return ResponseEntity.accepted().body(admin);
    }

    @DeleteMapping("/{adminId}")
    @PreAuthorize("hasRole('ADMIN')")
    public HttpStatus deleteAdmin(@PathVariable Long adminId){
        adminService.deleteAdmin(adminId);
        return HttpStatus.ACCEPTED;
    }
}
