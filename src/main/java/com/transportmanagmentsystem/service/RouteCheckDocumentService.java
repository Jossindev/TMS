package com.transportmanagmentsystem.service;

import com.transportmanagmentsystem.dto.RouteCheckDocumentDTO;
import com.transportmanagmentsystem.dto.RouteCheckDocumentUpdateDTO;
import com.transportmanagmentsystem.entity.CustomUser;
import com.transportmanagmentsystem.entity.Route;
import com.transportmanagmentsystem.entity.RouteCheckDocument;
import com.transportmanagmentsystem.entity.Schedule;

import java.time.LocalDateTime;
import java.util.List;

public interface RouteCheckDocumentService extends Service<RouteCheckDocumentDTO, RouteCheckDocument>{
    RouteCheckDocument update(RouteCheckDocumentUpdateDTO routeCheckDocumentUpdateDTO);

    List<RouteCheckDocument> findAllByDriverAndTripStatus(CustomUser driver, RouteCheckDocument.TripStatus tripStatus);

    List<RouteCheckDocument> findAllByDateAndRoute(LocalDateTime departureDateTime, Route route);

    List<RouteCheckDocument> findAllByScheduleIn(List<Schedule> schedules);
}
