package org.example.lic_policy_managament_system.service;

import lombok.RequiredArgsConstructor;
import org.example.lic_policy_managament_system.dto.LoginRequestDTO;
import org.example.lic_policy_managament_system.dto.LoginResponseDTO;
import org.example.lic_policy_managament_system.dto.UserRequestDTO;
import org.example.lic_policy_managament_system.dto.UserResponseDTO;
import org.example.lic_policy_managament_system.entity.User;
import org.example.lic_policy_managament_system.enums.Role;
import org.example.lic_policy_managament_system.repository.UserRepository;
import org.example.lic_policy_managament_system.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserResponseDTO createUser(UserRequestDTO request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.valueOf(request.getRole().toUpperCase()))
                .isActive(true)
                .build();

        User savedUser = userRepository.save(user);

        return UserResponseDTO.builder()
                .userId(savedUser.getUserId())
                .username(savedUser.getUsername())
                .email(savedUser.getEmail())
                .role(String.valueOf(savedUser.getRole()))
                .build();
    }
    public LoginResponseDTO login(LoginRequestDTO request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("Invalid Email"));

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword())) {

            throw new RuntimeException("Invalid Password");
        }

        String token = jwtService.generateToken(user.getEmail());

        return LoginResponseDTO.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole().name())
                .token(token)
                .message("Login Successful")
                .build();
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {

        return userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));
    }

    public void deleteUser(Long id) {

        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found");
        }

        userRepository.deleteById(id);
    }
}