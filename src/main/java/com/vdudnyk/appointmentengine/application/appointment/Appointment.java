package com.vdudnyk.appointmentengine.application.appointment;

import com.vdudnyk.appointmentengine.application.client.Client;
import com.vdudnyk.appointmentengine.application.salon.service.ServiceType;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
class Appointment {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Long salonId;
    @ManyToOne
    private Client client;
    @ManyToMany
    private List<ServiceType> serviceType;
    private LocalDateTime dateTimeFrom;
    private LocalDateTime dateTimeTo;
}
