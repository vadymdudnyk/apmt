package com.vdudnyk.appointmentengine.application.salon;

import com.vdudnyk.appointmentengine.application.salon.shared.AddServiceTypeRequest;
import com.vdudnyk.appointmentengine.application.salon.shared.AddWorkerToSalonRequest;
import com.vdudnyk.appointmentengine.application.salon.shared.CreateSalonRequest;
import com.vdudnyk.appointmentengine.application.salon.shared.SalonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SalonFacade {
    private final SalonService salonService;

    public SalonResponse createSalon(CreateSalonRequest createSalonRequest) {
        return salonService.createSalon(createSalonRequest);
    }

    public List<SalonResponse> addServiceType(AddServiceTypeRequest addServiceTypeRequest) {
        return salonService.addServiceType(addServiceTypeRequest);
    }

    public List<SalonResponse> getUserSalons() {
        return salonService.getUserSalons();
    }

    public SalonResponse addWorkerToSalon(AddWorkerToSalonRequest addWorkerToSalonRequest) {
        return salonService.addWorkerToSalon(addWorkerToSalonRequest);
    }
}
