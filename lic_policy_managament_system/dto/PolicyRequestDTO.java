package org.example.lic_policy_managament_system.dto;

import lombok.Data;

@Data
public class PolicyRequestDTO {

    private String policyName;
    private Double premiumAmount;
    private Double coverageAmount;
    private Integer durationYears;
    private Long customerId;
}