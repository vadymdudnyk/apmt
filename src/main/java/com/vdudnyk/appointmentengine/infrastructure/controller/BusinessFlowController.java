package com.vdudnyk.appointmentengine.infrastructure.controller;

import com.vdudnyk.appointmentengine.application.businessflow.BusinessFlowFacade;
import com.vdudnyk.appointmentengine.application.businessflow.shared.CreateUserAndSalonRequest;
import com.vdudnyk.appointmentengine.application.businessflow.shared.CreateUserAndSalonResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/business")
public class BusinessFlowController {
    private final BusinessFlowFacade businessFlowFacade;

    @PostMapping("/account")
    public ResponseEntity<CreateUserAndSalonResponse> createAccountAndSalon(@RequestBody CreateUserAndSalonRequest createUserAndSalonRequest) {
        CreateUserAndSalonResponse createUserAndSalonStatus = businessFlowFacade.createAccountAndSalon(createUserAndSalonRequest);

        return ResponseEntity.ok(createUserAndSalonStatus);
    }
}
