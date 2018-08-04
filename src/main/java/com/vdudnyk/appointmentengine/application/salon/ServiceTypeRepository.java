package com.vdudnyk.appointmentengine.application.salon;

import com.vdudnyk.appointmentengine.application.salon.service.ServiceType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceTypeRepository extends JpaRepository<ServiceType, Long> {
}
