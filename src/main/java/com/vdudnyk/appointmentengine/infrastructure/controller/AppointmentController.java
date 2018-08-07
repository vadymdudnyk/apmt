package com.vdudnyk.appointmentengine.infrastructure.controller;

import com.vdudnyk.appointmentengine.application.appointment.AppointmentFacade;
import com.vdudnyk.appointmentengine.application.appointment.shared.AppointmentDTO;
import com.vdudnyk.appointmentengine.application.appointment.shared.AppointmentsByDayDTO;
import com.vdudnyk.appointmentengine.application.appointment.shared.CreateAppointmentRequest;
import com.vdudnyk.appointmentengine.application.shared.StatusResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("appointments")
public class AppointmentController {
    private final AppointmentFacade appointmentFacade;

    @GetMapping
    public ResponseEntity<List<AppointmentDTO>> getAllAppointments() {
        return ResponseEntity.ok(appointmentFacade.getAllAppointments());
    }

    @GetMapping("/grouped")
    public ResponseEntity<List<AppointmentsByDayDTO>> getAllAppointmentsGroupedByDay() {
        return ResponseEntity.ok(appointmentFacade.getAllAppointmentsGroupedByDay());
    }

    @PostMapping
    public ResponseEntity<StatusResponse> createAppointment(@RequestBody CreateAppointmentRequest createAppointmentRequest) {
        return ResponseEntity.ok(appointmentFacade.createAppointment(createAppointmentRequest));
    }
}
