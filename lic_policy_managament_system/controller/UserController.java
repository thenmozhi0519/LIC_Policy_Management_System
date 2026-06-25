package org.example.lic_policy_managament_system.controller;
import lombok.RequiredArgsConstructor;
import org.example.lic_policy_managament_system.dto.LoginRequestDTO;
import org.example.lic_policy_managament_system.dto.LoginResponseDTO;
import org.example.lic_policy_managament_system.entity.User;
import org.example.lic_policy_managament_system.service.UserService;
import org.springframework.web.bind.annotation.*;
import org.example.lic_policy_managament_system.dto.UserRequestDTO;
import org.example.lic_policy_managament_system.dto.UserResponseDTO;
import java.util.List;
import jakarta.validation.Valid;
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/login")
    public LoginResponseDTO login(
            @RequestBody LoginRequestDTO request) {

        return userService.login(request);
    }
    @PostMapping
    public UserResponseDTO createUser(
           @Valid @RequestBody UserRequestDTO request) {

        return userService.createUser(request);
    }
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "User Deleted Successfully";
    }
}
