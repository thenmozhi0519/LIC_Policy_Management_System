package org.example.lic_policy_managament_system.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdminDashboardDTO {

    private long totalUsers;
    private long totalCustomers;
    private long totalPolicies;
    private long totalClaims;
}