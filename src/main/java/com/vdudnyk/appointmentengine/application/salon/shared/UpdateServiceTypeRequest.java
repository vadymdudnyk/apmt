package com.vdudnyk.appointmentengine.application.salon.shared;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpdateServiceTypeRequest {
    private Long id;
    private String serviceName;
    private BigDecimal price;
}
