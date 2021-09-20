package com.transportmanagmentsystem.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CUSTOM_USERS")
public class CustomUser {

    @Id
    @GeneratedValue
    private Long id;

    private String email;

    private String password;

    private String fullName;

    @Enumerated(EnumType.STRING)
    private CustomUserRole role;

    public enum CustomUserRole {
        DISPATCHER, DRIVER
    }
}
