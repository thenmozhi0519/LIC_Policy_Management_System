package org.example.lic_policy_managament_system.dto;

import lombok.Data;

@Data
public class ClaimRequestDTO {

    private Double claimAmount;
    private String claimReason;
    private Long policyId;
}