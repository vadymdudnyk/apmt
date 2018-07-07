package com.vdudnyk.appointmentengine.appointment;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(AppointmentController.RESOURCE)
public class AppointmentController {
    static final String RESOURCE = "appointments";

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping
    ResponseEntity<Appointment> createAppointment(@RequestBody CreateAppointmentRequest createAppointmentRequest) {
        Appointment appointment = appointmentService.createAppointment(createAppointmentRequest);
        return ResponseEntity
                .created(URI.create(RESOURCE + "/" + appointment.getId()))
                .body(appointment);
    }

    @GetMapping("/{id}")
    ResponseEntity<Appointment> getAppointment(@PathVariable Long id) {
        Optional<Appointment> appointment = appointmentService.getAppointment(id);
        return appointment.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    ResponseEntity<List<Appointment>> getAllAppointments() {
        return ResponseEntity.ok(appointmentService.getAllAppointments());
    }

    @PostMapping("/reserve")
    ResponseEntity<Appointment> reserveAppointment(@RequestBody ReserveAppointmentRequest reserveAppointmentRequest) {
        return appointmentService.reserveAppointment(reserveAppointmentRequest.getAppointmentId())
                                 .map(ResponseEntity::ok)
                                 .orElseGet(() -> ResponseEntity.unprocessableEntity().build());
    }

}
