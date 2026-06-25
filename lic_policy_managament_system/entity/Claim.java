package org.example.lic_policy_managament_system.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.example.lic_policy_managament_system.enums.ClaimStatus;

@Entity
@Table(name = "claims")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Claim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long claimId;

    private Double claimAmount;
    private String claimReason;
    private String claimStatus;

    @ManyToOne
    @JoinColumn(name = "policy_id")
    @JsonIgnore
    private Policy policy;
    @Enumerated(EnumType.STRING)
    private ClaimStatus status;
}