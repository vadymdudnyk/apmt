package com.vdudnyk.appointmentengine.infrastructure.controller;

import com.vdudnyk.appointmentengine.application.appointment.AppointmentFacade;
import com.vdudnyk.appointmentengine.application.appointment.shared.AppointmentDTO;
import com.vdudnyk.appointmentengine.application.appointment.shared.AppointmentsByDayDTO;
import com.vdudnyk.appointmentengine.application.appointment.shared.CreateAppointmentRequest;
import com.vdudnyk.appointmentengine.application.appointment.shared.UpdateAppointmentRequest;
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

    @GetMapping("/grouped/{currentDate}")
    public ResponseEntity<List<AppointmentsByDayDTO>> getAllAppointmentsGroupedByDayWholeMonth(@PathVariable String currentDate) {
        return ResponseEntity.ok(appointmentFacade.getAllAppointmentsGroupedByDayWholeMonth(currentDate));
    }

    @GetMapping("/grouped/{currentDate}/{weeksOffset}")
    public ResponseEntity<List<AppointmentsByDayDTO>> getAllAppointmentsGroupedByDayCurrentAndNextWeek(@PathVariable String currentDate,
                                                                                                       @PathVariable Long weeksOffset) {
        return ResponseEntity.ok(appointmentFacade.getAllAppointmentsGroupedByDayCurrentAndNextWeek(currentDate, weeksOffset));
    }

    @PostMapping
    public ResponseEntity<StatusResponse> createAppointment(@RequestBody CreateAppointmentRequest createAppointmentRequest) {
        return ResponseEntity.ok(appointmentFacade.createAppointment(createAppointmentRequest));
    }

    @PutMapping
    public ResponseEntity<StatusResponse> updateAppointment(@RequestBody UpdateAppointmentRequest updateAppointmentRequest) {
        return ResponseEntity.ok(appointmentFacade.updateAppointment(updateAppointmentRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StatusResponse> deleteAppointments(@PathVariable Long id) {
        return ResponseEntity.ok(appointmentFacade.deleteAppointment(id));
    }

}
