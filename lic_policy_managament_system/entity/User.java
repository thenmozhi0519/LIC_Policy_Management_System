package org.example.lic_policy_managament_system.entity;
import jakarta.persistence.*;
import lombok.*;
import org.example.lic_policy_managament_system.enums.Role;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String username;

    @Column(unique = true)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
    private Boolean isActive;

}
