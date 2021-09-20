package com.transportmanagmentsystem.service;

import java.util.List;
import java.util.Optional;

public interface Service<T, U> {
    U save(T t);

    Optional<U> findById(Long id);

    List<U> findAll();
}
