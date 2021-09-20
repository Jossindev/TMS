package com.transportmanagmentsystem.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ROUTES")
public class Route {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String number;

    private String departureAddress;

    private String arrivalAddress;

    private Float distance;
}
