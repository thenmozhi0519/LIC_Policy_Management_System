package org.example.lic_policy_managament_system.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    private String fullName;

    @Column(unique = true)
    private String phone;

    @Column(unique = true)
    private String aadhaarNumber;

    private String address;

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
}