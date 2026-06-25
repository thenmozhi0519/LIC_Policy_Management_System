package org.example.lic_policy_managament_system.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerResponseDTO {

    private Long customerId;
    private String fullName;
    private String phone;
    private String aadhaarNumber;
    private String address;
    private String email;
}