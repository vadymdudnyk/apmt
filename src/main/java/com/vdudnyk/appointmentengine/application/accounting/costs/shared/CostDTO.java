package com.vdudnyk.appointmentengine.application.accounting.costs.shared;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;
import java.util.List;

@Data
public class CostDTO {
    private Long id;

    private String name;

    private BigDecimal price;

    private Currency currency;

    private LocalDate date;

    private List<CategoryDTO> categories;

}
