package com.transportmanagmentsystem.entity;

import lombok.*;

import javax.persistence.*;
import java.util.LinkedHashMap;
import java.util.Map;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ROUTE_CHECK_DOCUMENTS")
public class RouteCheckDocument {

    @Id
    @GeneratedValue
    private Long id;

    private Long soldSeat;

    private Float filledFuel;

    @ManyToOne
    private CustomUser driver;

    @ManyToOne
    private Bus bus;

    @ManyToOne
    private Schedule schedule;

    private TripStatus tripStatus;

    public enum TripStatus {
        DRIVER_ACCEPTANCE_WAITING("trip-status.driver-acceptance-waiting"),
        REJECTED_BY_DRIVER("trip-status.rejected-by-driver"),
        ACCEPTED_BY_DRIVER("trip-status.accepted-by-driver"),
        READY_FOR_START("trip-status.ready-for-start"),
        ENDED("trip-status.canceled"),
        CANCELED("trip-status.ended");

        private final String localeProperty;

        TripStatus(String localeProperty) {
            this.localeProperty = localeProperty;
        }

        public String getLocaleProperty() {
            return localeProperty;
        }

        private static final Map<String, TripStatus> TRIP_STATUS_MAP = new LinkedHashMap<>();

        static {
            for (TripStatus tripStatus : TripStatus.values()) {
                TRIP_STATUS_MAP.put(tripStatus.name(), tripStatus);
            }
        }

        public static TripStatus forName(String name) {
            return TRIP_STATUS_MAP.get(name);
        }
    }
}
