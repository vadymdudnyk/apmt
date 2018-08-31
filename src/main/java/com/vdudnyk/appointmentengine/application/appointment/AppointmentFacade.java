package com.vdudnyk.appointmentengine.application.appointment;

import com.vdudnyk.appointmentengine.application.appointment.shared.AppointmentDTO;
import com.vdudnyk.appointmentengine.application.appointment.shared.AppointmentsByDayDTO;
import com.vdudnyk.appointmentengine.application.appointment.shared.CreateAppointmentRequest;
import com.vdudnyk.appointmentengine.application.appointment.shared.UpdateAppointmentRequest;
import com.vdudnyk.appointmentengine.application.shared.StatusResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AppointmentFacade {
    private final AppointmentService appointmentService;
    private final AppointmentsViewsService appointmentsViewsService;

    public StatusResponse createAppointment(CreateAppointmentRequest createAppointmentRequest) {
        return appointmentService.createAppointment(createAppointmentRequest);
    }

    public StatusResponse updateAppointment(UpdateAppointmentRequest updateAppointmentRequest) {
        return appointmentService.updateAppointment(updateAppointmentRequest);
    }

    public StatusResponse deleteAppointment(Long id) {
        return appointmentService.deleteAppointment(id);
    }

    public List<AppointmentDTO> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    public List<AppointmentsByDayDTO> getAllAppointmentsGroupedByDay() {
        return appointmentsViewsService.getAllAppointmentsGroupedByDay();
    }

    public List<AppointmentsByDayDTO> getAllAppointmentsGroupedByDayWholeMonth(String month) {
        return appointmentsViewsService.getAllAppointmentsGroupedByDayWholeMonth(month);
    }

    public List<AppointmentsByDayDTO> getAllAppointmentsGroupedByDayCurrentAndNextWeek(String currentDate, Long offset) {
        return appointmentsViewsService.getAllAppointmentsGroupedByDayCurrentAndNextWeek(currentDate, offset);
    }
}
