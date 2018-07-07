package com.vdudnyk.appointmentengine.salon;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

import static com.vdudnyk.appointmentengine.salon.SalonController.RESOURCE;

@RestController
@RequestMapping(RESOURCE)
@AllArgsConstructor
public class SalonController {
    static final String RESOURCE = "salons";

    private final SalonService salonService;

    @PostMapping
    public ResponseEntity<Salon> createSalon(@RequestBody CreateSalonRequest createSalonRequest) {
        Salon salon = salonService.createSalon(createSalonRequest);

        return ResponseEntity.created(URI.create(RESOURCE + "/" + salon.getId())).body(salon);
    }

    @PostMapping("/addWorker")
    public ResponseEntity<Salon> addWorker(@RequestBody AddWorkerToSalonRequest addWorkerToSalonRequest) {
        return ResponseEntity.ok(salonService.addWorkerToSalon(addWorkerToSalonRequest));
    }
}
