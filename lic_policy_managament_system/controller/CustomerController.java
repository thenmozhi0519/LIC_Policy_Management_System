package org.example.lic_policy_managament_system.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.lic_policy_managament_system.dto.CustomerRequestDTO;
import org.example.lic_policy_managament_system.dto.CustomerResponseDTO;
import org.example.lic_policy_managament_system.entity.Customer;
import org.example.lic_policy_managament_system.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public CustomerResponseDTO createCustomer(
           @Valid @RequestBody CustomerRequestDTO request) {

        return customerService.createCustomer(request);
    }
    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }
}