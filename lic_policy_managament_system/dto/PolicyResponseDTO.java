package org.example.lic_policy_managament_system.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PolicyResponseDTO {

    private Long policyId;
    private String policyName;
    private Double premiumAmount;
    private Double coverageAmount;
    private Integer durationYears;
    private String customerName;
}