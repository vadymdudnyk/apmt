package com.vdudnyk.appointmentengine.application.businessflow;

import com.vdudnyk.appointmentengine.application.businessflow.shared.CreateUserAndSalonRequest;
import com.vdudnyk.appointmentengine.application.businessflow.shared.CreateUserAndSalonResponse;
import com.vdudnyk.appointmentengine.application.salon.SalonFacade;
import com.vdudnyk.appointmentengine.application.salon.shared.CreateSalonRequest;
import com.vdudnyk.appointmentengine.application.salon.shared.SalonResponse;
import com.vdudnyk.appointmentengine.application.shared.Status;
import com.vdudnyk.appointmentengine.application.user.User;
import com.vdudnyk.appointmentengine.application.user.UserFacade;
import com.vdudnyk.appointmentengine.application.user.shared.RegisterUserRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
class BusinessFlowService {
    private final UserFacade userFacade;
    private final SalonFacade salonFacade;

    CreateUserAndSalonResponse createUserAndSalon(CreateUserAndSalonRequest createUserAndSalonRequest) {
        User user = userFacade.registerUser(new RegisterUserRequest(
                createUserAndSalonRequest.getFirstName(),
                createUserAndSalonRequest.getLastName(),
                createUserAndSalonRequest.getUsername(),
                createUserAndSalonRequest.getPassword(),
                createUserAndSalonRequest.isTacAcceptance()
        ));
        log.info("Registered user: {}", user.getUsername());

        SalonResponse createdSalon = salonFacade.createSalonForUser(new CreateSalonRequest("My Salon"), user);
        log.info("Registered salon: {}", createdSalon.getId());
        return new CreateUserAndSalonResponse(Status.SUCCESS);
    }

}
