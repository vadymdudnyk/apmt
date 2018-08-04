package com.vdudnyk.appointmentengine.application.salon.shared;

import com.vdudnyk.appointmentengine.application.salon.service.ServiceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Wither;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Currency;
import java.util.List;

@Wither
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SalonResponse {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private List<ServiceType> services;

    private Currency defaultCurrency;
}
