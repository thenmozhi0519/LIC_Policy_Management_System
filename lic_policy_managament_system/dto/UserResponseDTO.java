package org.example.lic_policy_managament_system.dto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponseDTO {
    private Long userId;
    private String username;
    private String email;
    private String role;
}
