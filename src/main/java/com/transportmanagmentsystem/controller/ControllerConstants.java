package com.transportmanagmentsystem.controller;

public interface ControllerConstants {

    String REDIRECT = "redirect:";

    interface Action {
        String SAVE = "/save";
        String UPDATE = "/update";
    }

    interface Mapping {
        String MAIN = "/main";
        String BUS_PARK_EMPLOYEES_WORK_CONTROL = "/busparkemployeesworkcontrol";

        String DRIVER_INFO_PERFORMING = "/driverinfoperforming";
        String MEDICAL_EXAMINATION_INFO_PERFORMING = "/medicalexaminationinfoperforming";
        String BUS_INFO_PERFORMING = "/businfoperforming";
        String BUS_SERVICE_LOG_PERFORMING = "/busservicelogperforming";
        String ROUTE_INFO_PERFORMING = "/routeinfoperforming";
        String SCHEDULE_INFO_PERFORMING = "/scheduleinfoperforming";
        String TRIP_INFO_PREFORMING = "/tripinfopreforming";
        String DRIVER_ASSIGNED_TRIP_LIST = "/driverassignedtriplist";
        String DRIVER_ACCEPTED_TRIP_LIST = "/driveracceptedtriplist";
        String LOGIN = "/login";
        String LOGOUT = "/logout";

        String SAVE_DRIVER_INFO = DRIVER_INFO_PERFORMING + Action.SAVE;
        String SAVE_MEDICAL_EXAMINATION_INFO = MEDICAL_EXAMINATION_INFO_PERFORMING + Action.SAVE;
        String SAVE_BUS_INFO = BUS_INFO_PERFORMING + Action.SAVE;
        String SAVE_BUS_SERVICE_LOG_NOTE = BUS_SERVICE_LOG_PERFORMING + Action.SAVE;
        String SAVE_ROUTE_INFO = ROUTE_INFO_PERFORMING + Action.SAVE;
        String SAVE_SCHEDULE_INFO = SCHEDULE_INFO_PERFORMING + Action.SAVE;
        String SAVE_TRIP_INFO = TRIP_INFO_PREFORMING + Action.SAVE;
        String UPDATE_TRIP_INFO = TRIP_INFO_PREFORMING + Action.UPDATE;
    }

    interface Pages {
        String MAIN = "main";
        String DRIVER_INFO_PERFORMING = "driver-info-performing";
        String MEDICAL_EXAMINATION_INFO_PERFORMING = "medical-examination-log-performing";
        String BUS_INFO_PERFORMING = "bus-info-performing";
        String BUS_SERVICE_LOG_PERFORMING = "bus-service-log-performing";
        String ROUTE_INFO_PERFORMING = "route-info-performing";
        String SCHEDULE_INFO_PERFORMING = "schedule-info-performing";
        String TRIP_INFO_PERFORMING = "trip-info-performing";
    }

    interface Model {
        String INFO_SAVED_ATTRIBUTE_KEY = "infoSaved";
        String INFO_UPDATED_ATTRIBUTE_KEY = "infoUpdated";
    }
}
