package com.vdudnyk.appointmentengine.salon;

import lombok.Data;

@Data
public class AddWorkerToSalonRequest {
    private Long salonId;
    private Long workerId;
}
