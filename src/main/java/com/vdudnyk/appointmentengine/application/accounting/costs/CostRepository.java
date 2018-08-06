package com.vdudnyk.appointmentengine.application.accounting.costs;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

interface CostRepository extends JpaRepository<Cost, Long> {
    List<Cost> findAllBySalonId(Long salonId);

    Optional<Cost> findByIdAndSalonId(Long id, Long salonId);
}
