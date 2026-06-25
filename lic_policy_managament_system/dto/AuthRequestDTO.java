package org.example.lic_policy_managament_system.dto;

import lombok.Data;

@Data
public class AuthRequestDTO {

    private String email;
    private String password;
}