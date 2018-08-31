package com.vdudnyk.appointmentengine.application.appointment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findAllBySalonId(Long salonId);

    List<Appointment> findAllBySalonIdAndDateTimeFromBetween(Long salonId, LocalDateTime localDateTimeFrom, LocalDateTime localDateTimeTo);

    Optional<Appointment> findByIdAndSalonId(Long id, Long salonId);
}
