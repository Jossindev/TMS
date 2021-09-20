package com.transportmanagmentsystem.repository;

import com.transportmanagmentsystem.entity.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomUserRepository extends JpaRepository<CustomUser, Long> {
    List<CustomUser> findAllByRole(CustomUser.CustomUserRole role);

    Optional<CustomUser> findByEmail(String email);
}
