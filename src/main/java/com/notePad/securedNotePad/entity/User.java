package com.notePad.securedNotePad.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = "usernames"),
            @UniqueConstraint(columnNames = "email")
    })
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;

    @NotBlank
    @Size(max = 50)
    @Column(name = "username")
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    @Column(name = "email")
    private String email;

    @NotBlank
    @Size(max = 120)
    @Column(name = "password")
    //@JsonIgnore
    private String password;

    private String userName;

    private LocalDate credentialsExpired;

    private LocalDate accountExpiryDate;

    private String twoFactorSecret;

    private boolean is2faEnabled = false;

    private String signUpMethod;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @UpdateTimestamp
    private LocalDateTime updatedDate;


    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "role_id", nullable = false, referencedColumnName = "role_id")
    //@ToString.Exclude
    //@JsonBackReference
    private Role role;
}
