package com.transportmanagmentsystem.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "SCHEDULE")
public class Schedule {

    @Id
    @GeneratedValue
    private Long id;

    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime departureDateTime;

    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime arrivalDateTime;

    @ManyToOne
    private Route route;
}
