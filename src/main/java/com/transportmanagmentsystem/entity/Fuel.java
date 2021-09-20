package com.transportmanagmentsystem.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "FUEL")
public class Fuel {

    @Id
    @GeneratedValue
    private Long id;

    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private FuelType fuelType;

    public enum FuelType {
        DIESEL("fuel-type.diezel"),
        BENZINE("fuel-type.benzine");

        private final String localeProperty;

        FuelType(String localeProperty) {
            this.localeProperty = localeProperty;
        }

        public String getLocaleProperty() {
            return localeProperty;
        }
    }
}
