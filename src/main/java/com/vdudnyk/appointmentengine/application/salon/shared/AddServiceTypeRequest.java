package com.vdudnyk.appointmentengine.application.salon.shared;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AddServiceTypeRequest {
    private String serviceName;
    private BigDecimal price;
}
