package com.vdudnyk.appointmentengine.application.accounting.costs;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;
import java.util.Set;

@Data
@Entity
class Cost {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private BigDecimal price;

    private Currency currency;

    private LocalDate date;

    @ManyToMany
    private Set<Category> categories;

    private Long salonId;
}
