package org.example.lic_policy_managament_system.service;

import lombok.RequiredArgsConstructor;
import org.example.lic_policy_managament_system.dto.CustomerRequestDTO;
import org.example.lic_policy_managament_system.dto.CustomerResponseDTO;
import org.example.lic_policy_managament_system.entity.Customer;
import org.example.lic_policy_managament_system.entity.User;
import org.example.lic_policy_managament_system.repository.CustomerRepository;
import org.example.lic_policy_managament_system.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public CustomerResponseDTO createCustomer(CustomerRequestDTO request) {

        if (customerRepository.existsByPhone(request.getPhone())) {
            throw new RuntimeException("Phone number already exists");
        }

        if (customerRepository.existsByAadhaarNumber(request.getAadhaarNumber())) {
            throw new RuntimeException("Aadhaar number already exists");
        }

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        if (customerRepository.existsByUser(user)) {
            throw new RuntimeException("Customer already exists for this user");
        }

        Customer customer = Customer.builder()
                .fullName(request.getFullName())
                .phone(request.getPhone())
                .aadhaarNumber(request.getAadhaarNumber())
                .address(request.getAddress())
                .user(user)
                .build();

        Customer savedCustomer = customerRepository.save(customer);

        return CustomerResponseDTO.builder()
                .customerId(savedCustomer.getCustomerId())
                .fullName(savedCustomer.getFullName())
                .phone(savedCustomer.getPhone())
                .aadhaarNumber(savedCustomer.getAadhaarNumber())
                .address(savedCustomer.getAddress())
                .email(savedCustomer.getUser().getEmail())
                .build();
    }
}