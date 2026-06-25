package org.example.lic_policy_managament_system.controller;

import lombok.RequiredArgsConstructor;
import org.example.lic_policy_managament_system.dto.ClaimRequestDTO;
import org.example.lic_policy_managament_system.dto.ClaimResponseDTO;
import org.example.lic_policy_managament_system.entity.Claim;
import org.example.lic_policy_managament_system.service.ClaimService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/claims")
@RequiredArgsConstructor
public class ClaimController {

    private final ClaimService claimService;

    @PostMapping
    public ClaimResponseDTO createClaim(
            @RequestBody ClaimRequestDTO request) {

        return claimService.createClaim(request);
    }
    @GetMapping
    public List<Claim> getAllClaims() {
        return claimService.getAllClaims();
    }
    @PutMapping("/{id}/approve")
    public Claim approveClaim(@PathVariable Long id) {

        return claimService.approveClaim(id);
    }

    @PutMapping("/{id}/reject")
    public Claim rejectClaim(@PathVariable Long id) {

        return claimService.rejectClaim(id);
    }
}