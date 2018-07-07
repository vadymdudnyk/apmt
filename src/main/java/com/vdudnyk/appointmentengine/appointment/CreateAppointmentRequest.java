package com.vdudnyk.appointmentengine.appointment;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class CreateAppointmentRequest {
    private LocalDateTime timeFrom;
    private LocalDateTime timeTo;
}
