package com.transportmanagmentsystem.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "BUSES")
public class Bus {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String registrationNumber;

    private Long seatAmount;

    private Float fuelTankVolume;

    private Float fuelConsumption;

    private Float mileage;

    private Float serviceRequiredAfterMileage;

    private Boolean requiredService;

    @ManyToOne
    private ComfortClass comfortClass;

    @ManyToOne
    private Fuel fuel;
}
