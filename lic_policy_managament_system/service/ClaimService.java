package org.example.lic_policy_managament_system.service;

import lombok.RequiredArgsConstructor;
import org.example.lic_policy_managament_system.dto.ClaimRequestDTO;
import org.example.lic_policy_managament_system.dto.ClaimResponseDTO;
import org.example.lic_policy_managament_system.entity.Claim;
import org.example.lic_policy_managament_system.entity.Policy;
import org.example.lic_policy_managament_system.repository.ClaimRepository;
import org.example.lic_policy_managament_system.repository.PolicyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClaimService {

    private final ClaimRepository claimRepository;
    private final PolicyRepository policyRepository;

    public List<Claim> getAllClaims() {
        return claimRepository.findAll();
    }
    public Claim approveClaim(Long claimId) {

        Claim claim = claimRepository.findById(claimId)
                .orElseThrow(() ->
                        new RuntimeException("Claim not found"));

        if(claim.getClaimStatus().equals("APPROVED")) {
            throw new RuntimeException("Claim already approved");
        }

        claim.setClaimStatus("APPROVED");

        return claimRepository.save(claim);
    }

    public Claim rejectClaim(Long claimId) {

        Claim claim = claimRepository.findById(claimId)
                .orElseThrow(() ->
                        new RuntimeException("Claim not found"));

        if(claim.getClaimStatus().equals("APPROVED")) {
            throw new RuntimeException("Approved claim cannot be rejected");
        }

        claim.setClaimStatus("REJECTED");

        return claimRepository.save(claim);

    }
    public ClaimResponseDTO createClaim(ClaimRequestDTO request) {

        Policy policy = policyRepository.findById(request.getPolicyId())
                .orElseThrow(() ->
                        new RuntimeException("Policy not found"));

        Claim claim = Claim.builder()
                .claimAmount(request.getClaimAmount())
                .claimReason(request.getClaimReason())
                .claimStatus("PENDING")
                .policy(policy)
                .build();

        Claim savedClaim = claimRepository.save(claim);

        return ClaimResponseDTO.builder()
                .claimId(savedClaim.getClaimId())
                .claimAmount(savedClaim.getClaimAmount())
                .claimReason(savedClaim.getClaimReason())
                .claimStatus(savedClaim.getClaimStatus())
                .policyName(savedClaim.getPolicy().getPolicyName())
                .build();
    }

}