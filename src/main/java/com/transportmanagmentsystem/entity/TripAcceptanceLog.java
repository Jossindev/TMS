package com.transportmanagmentsystem.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TRIP_ACCEPTANCE_LOGS")
public class TripAcceptanceLog {

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private TripAcceptanceStatus status;

    private String description;

    @ManyToOne
    private CustomUser driver;

    @ManyToOne
    private RouteCheckDocument routeCheckDocument;

    public enum TripAcceptanceStatus {
        ACCEPTED, DECLINED
    }
}
