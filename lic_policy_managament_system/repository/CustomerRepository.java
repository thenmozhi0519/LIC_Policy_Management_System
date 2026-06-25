package org.example.lic_policy_managament_system.repository;

import org.example.lic_policy_managament_system.entity.Customer;
import org.example.lic_policy_managament_system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    boolean existsByAadhaarNumber(String aadhaarNumber);
    boolean existsByUser(User user);
    boolean existsByPhone(String phone);
}