package com.vdudnyk.appointmentengine.application.businessflow;

import com.vdudnyk.appointmentengine.application.businessflow.shared.CreateUserAndSalonRequest;
import com.vdudnyk.appointmentengine.application.businessflow.shared.CreateUserAndSalonResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BusinessFlowFacade {
    private final BusinessFlowService businessFlowService;

    public CreateUserAndSalonResponse createAccountAndSalon(CreateUserAndSalonRequest createUserAndSalonRequest) {
        return businessFlowService.createUserAndSalon(createUserAndSalonRequest);
    }
}
