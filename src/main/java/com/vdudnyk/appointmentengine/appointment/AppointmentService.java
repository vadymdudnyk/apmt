package com.vdudnyk.appointmentengine.appointment;

import com.vdudnyk.appointmentengine.user.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
class AppointmentService {

    private AppointmentRepository appointmentRepository;
    private UserService userService;

    Appointment createAppointment(CreateAppointmentRequest createAppointmentRequest) {
        Appointment appointment = new Appointment();
        appointment.setTimeFrom(createAppointmentRequest.getTimeFrom());
        appointment.setTimeTo(createAppointmentRequest.getTimeTo());
        return appointmentRepository.save(appointment);
    }

    Optional<Appointment> getAppointment(Long appointmentId) {
        return appointmentRepository.findById(appointmentId);
    }

    List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    Optional<Appointment> reserveAppointment(Long id) {
        Optional<Appointment> possibleAppointment = appointmentRepository.findById(id);
        return possibleAppointment
                .filter(appointment -> appointment.getReservedBy() == null)
                .map(appointment -> appointment.withReservedBy(userService.getCurrentUser()))
                .map(appointmentRepository::save);
    }
}
