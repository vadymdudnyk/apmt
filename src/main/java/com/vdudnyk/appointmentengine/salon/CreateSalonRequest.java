package com.vdudnyk.appointmentengine.salon;

import lombok.Data;

@Data
public class CreateSalonRequest {
    private String name;
    private Long ownerId;
}
