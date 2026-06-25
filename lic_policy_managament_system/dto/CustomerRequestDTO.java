package org.example.lic_policy_managament_system.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CustomerRequestDTO {

    private String fullName;
    @NotBlank
    @Pattern(
            regexp = "^[0-9]{10}$",
            message = "Phone number must be exactly 10 digits"
    )
    private String phone;

    @NotBlank
    @Pattern(
            regexp = "^[0-9]{12}$",
            message = "Aadhaar must be exactly 12 digits"
    )
    private String aadhaarNumber;
    private String address;
    private Long userId;
}