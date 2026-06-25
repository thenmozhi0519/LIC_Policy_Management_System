package org.example.lic_policy_managament_system.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "policies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Policy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long policyId;

    private String policyName;

    private Double premiumAmount;

    private Double coverageAmount;

    private Integer durationYears;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}