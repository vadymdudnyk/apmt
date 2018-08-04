package com.vdudnyk.appointmentengine.application.salon;

import com.vdudnyk.appointmentengine.application.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface SalonRepository extends JpaRepository<Salon, Long> {
    List<Salon> findAllByOwner(User user);
}
