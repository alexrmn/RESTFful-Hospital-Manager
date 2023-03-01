package ro.alexrmn.hospitalmanagerbackend.controller;

import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.alexrmn.hospitalmanagerbackend.model.User;
import ro.alexrmn.hospitalmanagerbackend.model.dto.CreateUserDto;
import ro.alexrmn.hospitalmanagerbackend.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {

    private final UserService userService;

    @PostMapping("/register")
    @PermitAll
    public User createNewUser(@RequestBody CreateUserDto createUserDto) {
        return userService.createUser(createUserDto);
    }
}
