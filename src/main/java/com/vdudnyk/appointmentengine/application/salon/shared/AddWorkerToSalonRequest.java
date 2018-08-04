package com.vdudnyk.appointmentengine.application.salon.shared;

import lombok.Data;

@Data
public class AddWorkerToSalonRequest {
    private Long salonId;
    private Long workerId;
}
