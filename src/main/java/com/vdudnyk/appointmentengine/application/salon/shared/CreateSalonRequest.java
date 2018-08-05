package com.vdudnyk.appointmentengine.application.salon.shared;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateSalonRequest {
    private String name;
}
