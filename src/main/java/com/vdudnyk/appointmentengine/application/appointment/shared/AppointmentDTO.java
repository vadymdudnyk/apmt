package com.vdudnyk.appointmentengine.application.appointment.shared;

import com.vdudnyk.appointmentengine.application.client.Client;
import com.vdudnyk.appointmentengine.application.salon.service.ServiceType;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.List;

@Value
public class AppointmentDTO {
    private Long id;

    private Long salonId;
    private Client client;

    private List<ServiceType> serviceType;

    private LocalDateTime dateTimeFrom;
    private LocalDateTime dateTimeTo;
}
