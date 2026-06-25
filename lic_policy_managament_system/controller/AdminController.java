package org.example.lic_policy_managament_system.controller;

import lombok.RequiredArgsConstructor;
import org.example.lic_policy_managament_system.dto.AdminDashboardDTO;
import org.example.lic_policy_managament_system.repository.ClaimRepository;
import org.example.lic_policy_managament_system.repository.CustomerRepository;
import org.example.lic_policy_managament_system.repository.PolicyRepository;
import org.example.lic_policy_managament_system.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final PolicyRepository policyRepository;
    private final ClaimRepository claimRepository;

    @GetMapping("/dashboard")
    public AdminDashboardDTO dashboard() {

        return AdminDashboardDTO.builder()
                .totalUsers(userRepository.count())
                .totalCustomers(customerRepository.count())
                .totalPolicies(policyRepository.count())
                .totalClaims(claimRepository.count())
                .build();
    }
}