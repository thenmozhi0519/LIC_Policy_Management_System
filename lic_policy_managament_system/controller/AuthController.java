package org.example.lic_policy_managament_system.controller;

import lombok.RequiredArgsConstructor;
import org.example.lic_policy_managament_system.dto.AuthRequestDTO;
import org.example.lic_policy_managament_system.dto.AuthResponseDTO;
import org.example.lic_policy_managament_system.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public AuthResponseDTO login(
            @RequestBody AuthRequestDTO request) {

        return authService.login(request);
    }
}