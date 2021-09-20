package com.transportmanagmentsystem.service;

import com.transportmanagmentsystem.dto.TripAcceptanceForm;
import com.transportmanagmentsystem.entity.TripAcceptanceLog;

public interface TripAcceptanceService {
    TripAcceptanceLog save(TripAcceptanceForm tripAcceptanceForm, TripAcceptanceLog.TripAcceptanceStatus tripAcceptanceStatus);
}
