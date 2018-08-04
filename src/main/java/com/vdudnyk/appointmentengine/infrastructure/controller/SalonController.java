package com.vdudnyk.appointmentengine.infrastructure.controller;

import com.vdudnyk.appointmentengine.application.salon.SalonFacade;
import com.vdudnyk.appointmentengine.application.salon.shared.AddServiceTypeRequest;
import com.vdudnyk.appointmentengine.application.salon.shared.AddWorkerToSalonRequest;
import com.vdudnyk.appointmentengine.application.salon.shared.CreateSalonRequest;
import com.vdudnyk.appointmentengine.application.salon.shared.SalonResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static com.vdudnyk.appointmentengine.infrastructure.controller.SalonController.RESOURCE;

@RestController
@RequestMapping(RESOURCE)
@AllArgsConstructor
public class SalonController {
    static final String RESOURCE = "salons";

    private final SalonFacade salonFacade;

    @PostMapping
    public ResponseEntity<SalonResponse> createSalon(@RequestBody CreateSalonRequest createSalonRequest) {
        SalonResponse salon = salonFacade.createSalon(createSalonRequest);

        return ResponseEntity.created(URI.create(RESOURCE + "/" + salon.getId())).body(salon);
    }

    @PostMapping("/services")
    public ResponseEntity<List<SalonResponse>> addServiceType(@RequestBody AddServiceTypeRequest addServiceTypeRequest) {
        List<SalonResponse> salonResponses = salonFacade.addServiceType(addServiceTypeRequest);
        return ResponseEntity.ok(salonResponses);
    }

    @GetMapping
    public ResponseEntity<List<SalonResponse>> getUserSalons() {
        List<SalonResponse> userSalons = salonFacade.getUserSalons();

        return ResponseEntity.ok(userSalons);
    }

    @PostMapping("/addWorker")
    public ResponseEntity<SalonResponse> addWorker(@RequestBody AddWorkerToSalonRequest addWorkerToSalonRequest) {
        return ResponseEntity.ok(salonFacade.addWorkerToSalon(addWorkerToSalonRequest));
    }
}
