package com.vdudnyk.appointmentengine.application.salon;

import com.vdudnyk.appointmentengine.application.salon.service.ServiceType;
import com.vdudnyk.appointmentengine.application.user.User;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

@Entity
@Data
class Salon {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne
    private User owner;

    @ManyToMany
    private List<User> workers;

    @ManyToMany
    private List<ServiceType> services = new ArrayList<>();

    private Currency defaultCurrency;

}
