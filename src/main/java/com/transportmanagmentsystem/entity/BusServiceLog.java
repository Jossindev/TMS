package com.transportmanagmentsystem.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "BUS_SERVICE_LOGS")
public class BusServiceLog {

    @Id
    @GeneratedValue
    private Long id;

    private LocalDateTime serviceDate;

    private Float mileage;

    @ManyToOne
    private Bus bus;
}
