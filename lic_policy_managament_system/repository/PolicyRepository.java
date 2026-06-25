package org.example.lic_policy_managament_system.repository;

import org.example.lic_policy_managament_system.entity.Policy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PolicyRepository
        extends JpaRepository<Policy, Long> {
}