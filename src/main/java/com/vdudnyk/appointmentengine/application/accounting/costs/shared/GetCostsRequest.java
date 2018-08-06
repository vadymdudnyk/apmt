package com.vdudnyk.appointmentengine.application.accounting.costs.shared;

import lombok.Data;

import java.time.LocalDate;

@Data
public class GetCostsRequest {
    private LocalDate dateFrom;
    private LocalDate dateTo;
}
