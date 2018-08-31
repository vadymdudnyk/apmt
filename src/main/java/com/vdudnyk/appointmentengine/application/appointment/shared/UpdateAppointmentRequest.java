package com.vdudnyk.appointmentengine.application.appointment.shared;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class UpdateAppointmentRequest {
    private Long id;
    private String name;
    private List<Long> serviceType;
    private LocalDateTime dateTimeFrom;
    private LocalDateTime dateTimeTo;
}
