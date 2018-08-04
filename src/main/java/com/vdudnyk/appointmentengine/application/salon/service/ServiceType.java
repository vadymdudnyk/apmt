package com.vdudnyk.appointmentengine.application.salon.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Wither;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Data
@Wither
@AllArgsConstructor
@NoArgsConstructor
public class ServiceType {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private BigDecimal price;
}
