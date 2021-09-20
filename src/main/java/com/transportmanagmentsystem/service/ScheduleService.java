package com.transportmanagmentsystem.service;

import com.transportmanagmentsystem.dto.ScheduleDTO;
import com.transportmanagmentsystem.entity.Route;
import com.transportmanagmentsystem.entity.Schedule;

import java.time.LocalDateTime;
import java.util.List;

public interface ScheduleService extends Service<ScheduleDTO, Schedule> {
    List<Schedule> findByArrivalDateTimeAfterAndAndArrivalDateTimeBeforeAndRoute(LocalDateTime from, Route route);
}
