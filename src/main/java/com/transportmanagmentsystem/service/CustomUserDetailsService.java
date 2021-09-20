package com.transportmanagmentsystem.service;

import com.transportmanagmentsystem.dto.CustomUserDTO;
import com.transportmanagmentsystem.entity.CustomUser;

import java.util.List;
import java.util.Optional;

public interface CustomUserDetailsService extends Service<CustomUserDTO, CustomUser>{
    CustomUser save(CustomUserDTO customUserDTO);

    Optional<CustomUser> findByEmail(String email);

    List<CustomUser> findAllByRole(CustomUser.CustomUserRole role);

    CustomUser findCurrentSessionDriver();
}
