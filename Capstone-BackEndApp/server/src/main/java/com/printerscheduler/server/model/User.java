package com.printerscheduler.server.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;

@Entity
@Data                   // Generate Getters/Setters & More
@NoArgsConstructor      // Generate No Arg Constructor
@AllArgsConstructor     // Generate All Args Constructor
@Table(name = "user")
public class User implements Serializable {
    // Generated id value for database
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true, length = 40)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private int hoursReserved;

    @Column(nullable = false)
    boolean approved;

    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;

    // Limit to 10 hours per week
    private final int msgLimit = 10;
}
