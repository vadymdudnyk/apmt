package com.vdudnyk.appointmentengine.application.appointment.shared;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CreateAppointmentRequest {
    private String name;
    private List<Long> serviceTypes;
    private LocalDateTime dateTimeFrom;
    private LocalDateTime dateTimeTo;
    private Long clientId;
}
