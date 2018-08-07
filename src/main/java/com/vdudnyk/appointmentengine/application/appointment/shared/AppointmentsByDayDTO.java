package com.vdudnyk.appointmentengine.application.appointment.shared;

import lombok.Value;

import java.time.LocalDate;
import java.util.List;

@Value
public class AppointmentsByDayDTO {
    private LocalDate dayOfMonth;
    private List<AppointmentDTO> appointments;
}
