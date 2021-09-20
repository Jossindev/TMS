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
@Table(name = "COMFORT_CLASSES")
public class ComfortClass {

    @Id
    @GeneratedValue
    private Long id;

    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private ComfortClassType comfortClassType;


    public enum ComfortClassType {
        SECOND_CLASS("comfort-class-type.second"),
        FIRST_CLASS("comfort-class-type.first");

        private final String localeProperty;

        ComfortClassType(String localeProperty) {
            this.localeProperty = localeProperty;
        }

        public String getLocaleProperty() {
            return localeProperty;
        }
    }
}
