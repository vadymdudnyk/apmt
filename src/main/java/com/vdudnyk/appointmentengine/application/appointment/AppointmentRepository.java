package com.vdudnyk.appointmentengine.application.appointment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findAllBySalonId(Long salonId);

    List<Appointment> findAllBySalonIdAndDateTimeFromBetween(Long salonId, LocalDateTime localDateTimeFrom, LocalDateTime localDateTimeTo);

}
