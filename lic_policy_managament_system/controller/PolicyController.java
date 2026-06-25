package org.example.lic_policy_managament_system.controller;

import lombok.RequiredArgsConstructor;
import org.example.lic_policy_managament_system.dto.PolicyRequestDTO;
import org.example.lic_policy_managament_system.dto.PolicyResponseDTO;
import org.example.lic_policy_managament_system.entity.Policy;
import org.example.lic_policy_managament_system.service.PolicyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/policies")
@RequiredArgsConstructor
public class PolicyController {

    private final PolicyService policyService;

    @PostMapping
    public PolicyResponseDTO createPolicy(
            @RequestBody PolicyRequestDTO request) {

        return policyService.createPolicy(request);
    }
    @GetMapping
    public List<Policy> getAllPolicies() {
        return policyService.getAllPolicies();
    }
}