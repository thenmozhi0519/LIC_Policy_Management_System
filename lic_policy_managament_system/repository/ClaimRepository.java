package org.example.lic_policy_managament_system.repository;

import org.example.lic_policy_managament_system.entity.Claim;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClaimRepository extends JpaRepository<Claim, Long> {
}