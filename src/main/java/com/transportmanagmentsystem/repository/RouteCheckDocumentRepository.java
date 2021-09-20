package com.transportmanagmentsystem.repository;

import com.transportmanagmentsystem.entity.CustomUser;
import com.transportmanagmentsystem.entity.RouteCheckDocument;
import com.transportmanagmentsystem.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RouteCheckDocumentRepository extends JpaRepository<RouteCheckDocument, Long> {
    List<RouteCheckDocument> findAllByDriverAndAndTripStatus(CustomUser driver, RouteCheckDocument.TripStatus tripStatus);

    List<RouteCheckDocument> findAllByScheduleIn(List<Schedule> schedules);
}
