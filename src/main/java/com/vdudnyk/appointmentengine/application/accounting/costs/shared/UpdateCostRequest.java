package com.vdudnyk.appointmentengine.application.accounting.costs.shared;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class UpdateCostRequest {
    private Long id;
    private String name;

    private BigDecimal price;

    private LocalDate date;

    private List<String> categories;
}
