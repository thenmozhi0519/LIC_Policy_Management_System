package org.example.lic_policy_managament_system.service;

import lombok.RequiredArgsConstructor;
import org.example.lic_policy_managament_system.dto.AuthRequestDTO;
import org.example.lic_policy_managament_system.dto.AuthResponseDTO;
import org.example.lic_policy_managament_system.entity.User;
import org.example.lic_policy_managament_system.repository.UserRepository;
import org.example.lic_policy_managament_system.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthResponseDTO login(AuthRequestDTO request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("Invalid Email"));

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword())) {

            throw new RuntimeException("Invalid Password");
        }

        String token =
                jwtService.generateToken(user.getEmail());

        return new AuthResponseDTO(token);
    }
}