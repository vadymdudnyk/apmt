package com.vdudnyk.appointmentengine.application.appointment;

import com.vdudnyk.appointmentengine.application.appointment.shared.AppointmentDTO;
import com.vdudnyk.appointmentengine.application.appointment.shared.AppointmentsByDayDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.*;
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

    List<AppointmentsByDayDTO> getAllAppointmentsGroupedByDayWholeMonth(String month) {
        LocalDate localDate = LocalDate.parse(month);
        List<AppointmentDTO> allAppointments = appointmentService.getAllAppointments(localDate.withDayOfMonth(1),
                                                                                     localDate.withDayOfMonth(localDate.lengthOfMonth()));
        Map<LocalDate, AppointmentsByDayDTO> allDaysOfMonth = groupByDays(localDate.withDayOfMonth(1), localDate.withDayOfMonth(localDate.lengthOfMonth()));
        allAppointments.forEach(appointmentDTO -> allDaysOfMonth.get(appointmentDTO.getDateTimeFrom().toLocalDate())
                                                                .getAppointments().add(appointmentDTO));
        return new ArrayList<>(allDaysOfMonth.values());
    }

    List<AppointmentsByDayDTO> getAllAppointmentsGroupedByDayCurrentAndNextWeek(String currentDate, Long weeksOffset) {
        LocalDate localDate = LocalDate.parse(currentDate);
        LocalDate startWeek = localDate.with(WeekFields.of(Locale.FRANCE).dayOfWeek(), 1L)
                                       .plusWeeks(weeksOffset);
        List<AppointmentDTO> allAppointments = appointmentService.getAllAppointments(startWeek,
                                                                                     startWeek.plusWeeks(2));

        Map<LocalDate, AppointmentsByDayDTO> groupedByDay = groupByDays(startWeek, startWeek.plusWeeks(2));
        allAppointments.forEach(appointmentDTO -> groupedByDay.get(appointmentDTO.getDateTimeFrom().toLocalDate())
                                                              .getAppointments().add(appointmentDTO));
        return groupedByDay.values()
                           .stream()
                           .sorted(Comparator.comparing(AppointmentsByDayDTO::getDayOfMonth))
                           .collect(Collectors.toList());

    }

    private Map<LocalDate, AppointmentsByDayDTO> groupByDays(LocalDate startDay, LocalDate lastDay) {
        Map<LocalDate, AppointmentsByDayDTO> allDaysOfMonth = new HashMap<>();
        int i = 0;
        while (startDay.plusDays(i).isBefore(lastDay)) {
            allDaysOfMonth.put(startDay.plusDays(i), new AppointmentsByDayDTO(
                    startDay.plusDays(i),
                    new ArrayList<>()
            ));
            i++;
        }
        return allDaysOfMonth;
    }
}
