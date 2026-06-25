package org.example.lic_policy_managament_system.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClaimResponseDTO {

    private Long claimId;
    private Double claimAmount;
    private String claimReason;
    private String claimStatus;
    private String policyName;
}