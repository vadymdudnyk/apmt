package com.vdudnyk.appointmentengine.application.appointment;

import com.vdudnyk.appointmentengine.application.appointment.shared.AppointmentDTO;
import com.vdudnyk.appointmentengine.application.appointment.shared.AppointmentsByDayDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AppointmentsViewsService {
    private final AppointmentService appointmentService;

    List<AppointmentsByDayDTO> getAllAppointmentsGroupedByDay() {
        List<AppointmentDTO> allAppointments = appointmentService.getAllAppointments();

        Map<LocalDate, List<AppointmentDTO>> collect = allAppointments.stream()
                                                                      .collect(Collectors.groupingBy(appointmentDTO -> appointmentDTO.getDateTimeFrom()
                                                                                                                                     .toLocalDate()));
        return collect.keySet().stream().map(localDate -> new AppointmentsByDayDTO(localDate, collect.get(localDate))).collect(Collectors.toList());
    }
}
