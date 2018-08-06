package com.vdudnyk.appointmentengine.application.accounting.costs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Wither;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
class Category {
    @Id
    @GeneratedValue
    private Long id;

    @Wither
    private String name;
}
