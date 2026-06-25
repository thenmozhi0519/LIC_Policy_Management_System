package org.example.lic_policy_managament_system.service;

import lombok.RequiredArgsConstructor;
import org.example.lic_policy_managament_system.dto.PolicyRequestDTO;
import org.example.lic_policy_managament_system.dto.PolicyResponseDTO;
import org.example.lic_policy_managament_system.entity.Customer;
import org.example.lic_policy_managament_system.entity.Policy;
import org.example.lic_policy_managament_system.repository.CustomerRepository;
import org.example.lic_policy_managament_system.repository.PolicyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PolicyService {

    private final PolicyRepository policyRepository;
    private final CustomerRepository customerRepository;

    public List<Policy> getAllPolicies() {
        return policyRepository.findAll();
    }
    public PolicyResponseDTO createPolicy(PolicyRequestDTO request) {

        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() ->
                        new RuntimeException("Customer not found"));

        Policy policy = Policy.builder()
                .policyName(request.getPolicyName())
                .premiumAmount(request.getPremiumAmount())
                .coverageAmount(request.getCoverageAmount())
                .durationYears(request.getDurationYears())
                .customer(customer)
                .build();

        Policy savedPolicy = policyRepository.save(policy);

        return PolicyResponseDTO.builder()
                .policyId(savedPolicy.getPolicyId())
                .policyName(savedPolicy.getPolicyName())
                .premiumAmount(savedPolicy.getPremiumAmount())
                .coverageAmount(savedPolicy.getCoverageAmount())
                .durationYears(savedPolicy.getDurationYears())
                .customerName(savedPolicy.getCustomer().getFullName())
                .build();
    }
}