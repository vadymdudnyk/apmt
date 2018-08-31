package com.vdudnyk.appointmentengine.application.appointment;

import com.vdudnyk.appointmentengine.application.appointment.shared.AppointmentDTO;
import com.vdudnyk.appointmentengine.application.appointment.shared.CreateAppointmentRequest;
import com.vdudnyk.appointmentengine.application.appointment.shared.UpdateAppointmentRequest;
import com.vdudnyk.appointmentengine.application.salon.SalonFacade;
import com.vdudnyk.appointmentengine.application.salon.shared.SalonResponse;
import com.vdudnyk.appointmentengine.application.shared.Status;
import com.vdudnyk.appointmentengine.application.shared.StatusResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
class AppointmentService {
    private final SalonFacade salonFacade;
    private final AppointmentRepository appointmentRepository;

    StatusResponse createAppointment(CreateAppointmentRequest createAppointmentRequest) {
        SalonResponse userSalon = salonFacade.getUserSalon();
        Appointment appointment = new Appointment();
        appointment.setName(createAppointmentRequest.getName());
        appointment.setSalonId(userSalon.getId());
        appointment.setDateTimeFrom(createAppointmentRequest.getDateTimeFrom());
        appointment.setDateTimeTo(createAppointmentRequest.getDateTimeTo());
        appointment.setServiceType(userSalon.getServices()
                                            .stream()
                                            .filter(serviceType -> createAppointmentRequest.getServiceTypes().contains(serviceType.getId()))
                                            .collect(Collectors.toList()));
        appointmentRepository.save(appointment);
        return new StatusResponse(Status.SUCCESS);
    }

    StatusResponse deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
        return new StatusResponse(Status.SUCCESS);
    }

    List<AppointmentDTO> getAllAppointments() {
        SalonResponse userSalon = salonFacade.getUserSalon();
        return appointmentRepository
                .findAllBySalonId(userSalon.getId())
                .stream()
                .map(this::mapAppointmentToAppointmentDTO)
                .collect(Collectors.toList());
    }

    List<AppointmentDTO> getAllAppointments(LocalDate localDateFrom, LocalDate localDateTo) {
        SalonResponse userSalon = salonFacade.getUserSalon();

        return appointmentRepository
                .findAllBySalonIdAndDateTimeFromBetween(userSalon.getId(),
                                                        localDateFrom.atStartOfDay(),
                                                        localDateTo.atTime(23, 59, 59))
                .stream()
                .map(this::mapAppointmentToAppointmentDTO)
                .collect(Collectors.toList());
    }

    StatusResponse updateAppointment(UpdateAppointmentRequest updateAppointmentRequest) {
        SalonResponse userSalon = salonFacade.getUserSalon();

        Optional<Appointment> appointmentOptional = appointmentRepository
                .findByIdAndSalonId(updateAppointmentRequest.getId(), userSalon.getId());
        appointmentOptional.ifPresent(appointment -> {
            appointment.setDateTimeTo(updateAppointmentRequest.getDateTimeTo());
            appointment.setDateTimeFrom(updateAppointmentRequest.getDateTimeFrom());
            appointment.setServiceType(userSalon.getServices()
                                                .stream()
                                                .filter(serviceType -> updateAppointmentRequest.getServiceType().contains(serviceType.getId()))
                                                .collect(Collectors.toList()));
            appointmentRepository.save(appointment);
        });
        return new StatusResponse(Status.SUCCESS);
    }

    private AppointmentDTO mapAppointmentToAppointmentDTO(Appointment appointment) {
        return new AppointmentDTO(appointment.getId(),
                                  appointment.getName(),
                                  appointment.getSalonId(),
                                  appointment.getClient(),
                                  appointment.getServiceType(),
                                  appointment.getDateTimeFrom(),
                                  appointment.getDateTimeTo()
        );
    }

}
