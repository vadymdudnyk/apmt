package com.vdudnyk.appointmentengine.infrastructure.controller;

import com.vdudnyk.appointmentengine.application.client.ClientFacade;
import com.vdudnyk.appointmentengine.application.salon.SalonFacade;
import com.vdudnyk.appointmentengine.application.salon.shared.AddServiceTypeRequest;
import com.vdudnyk.appointmentengine.application.salon.shared.AddWorkerToSalonRequest;
import com.vdudnyk.appointmentengine.application.salon.shared.SalonResponse;
import com.vdudnyk.appointmentengine.application.salon.shared.UpdateServiceTypeRequest;
import com.vdudnyk.appointmentengine.application.shared.StatusResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.vdudnyk.appointmentengine.infrastructure.controller.SalonController.RESOURCE;

@RestController
@RequestMapping(RESOURCE)
@AllArgsConstructor
public class SalonController {
    static final String RESOURCE = "salon";

    private final SalonFacade salonFacade;
    private final ClientFacade clientFacade;

    @PostMapping("/services")
    public ResponseEntity<SalonResponse> addServiceType(@RequestBody AddServiceTypeRequest addServiceTypeRequest) {
        SalonResponse salonResponse = salonFacade.addServiceType(addServiceTypeRequest);
        return ResponseEntity.ok(salonResponse);
    }

    @DeleteMapping("/services/{id}")
    public ResponseEntity<StatusResponse> deleteServiceType(@PathVariable Long id) {
        StatusResponse statusResponse = salonFacade.deleteServiceType(id);
        return ResponseEntity.ok(statusResponse);
    }

    @PutMapping("/services")
    public ResponseEntity<StatusResponse> updateServiceType(@RequestBody UpdateServiceTypeRequest updateServiceTypeRequest) {
        StatusResponse statusResponse = salonFacade.updateServiceType(updateServiceTypeRequest);
        return ResponseEntity.ok(statusResponse);
    }


    @GetMapping
    public ResponseEntity<SalonResponse> getUserSalon() {
        SalonResponse userSalon = salonFacade.getUserSalon();

        return ResponseEntity.ok(userSalon);
    }

    @PostMapping("/addWorker")
    public ResponseEntity<SalonResponse> addWorker(@RequestBody AddWorkerToSalonRequest addWorkerToSalonRequest) {
        return ResponseEntity.ok(salonFacade.addWorkerToSalon(addWorkerToSalonRequest));
    }
}
