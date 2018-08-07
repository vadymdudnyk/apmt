package com.vdudnyk.appointmentengine.application.client;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByIdAndSalonId(Long id, Long salonId);
}
