package com.vdudnyk.appointmentengine.application.appointment.shared;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CreateAppointmentRequest {

    private List<Long> serviceTypes;
    private LocalDateTime dateTimeFrom;
    private LocalDateTime dateTimeTo;
    private Client client;

    @Data
    public class Client {
        private Long id;
        private String firstName;
        private String lastName;
        private String phoneNumber;
        private String socialMediaLink;
    }
}
