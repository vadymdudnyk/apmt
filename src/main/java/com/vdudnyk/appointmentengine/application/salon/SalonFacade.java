package com.vdudnyk.appointmentengine.application.salon;

import com.vdudnyk.appointmentengine.application.salon.shared.*;
import com.vdudnyk.appointmentengine.application.shared.StatusResponse;
import com.vdudnyk.appointmentengine.application.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SalonFacade {
    private final SalonService salonService;

    public SalonResponse createSalonForUser(CreateSalonRequest createSalonRequest, User user) {
        return salonService.createSalonForUser(createSalonRequest, user);
    }

    public SalonResponse addServiceType(AddServiceTypeRequest addServiceTypeRequest) {
        return salonService.addServiceType(addServiceTypeRequest);
    }

    public SalonResponse getUserSalon() {
        return salonService.getUserSalon();
    }

    public StatusResponse deleteServiceType(Long id) {
        return salonService.deleteServiceType(id);
    }

    public StatusResponse updateServiceType(UpdateServiceTypeRequest updateServiceTypeRequest) {
        return salonService.updateServiceType(updateServiceTypeRequest);
    }

    public SalonResponse addWorkerToSalon(AddWorkerToSalonRequest addWorkerToSalonRequest) {
        return salonService.addWorkerToSalon(addWorkerToSalonRequest);
    }

}
