package com.vdudnyk.appointmentengine.application.appointment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findAllBySalonId(Long salonId);

}
