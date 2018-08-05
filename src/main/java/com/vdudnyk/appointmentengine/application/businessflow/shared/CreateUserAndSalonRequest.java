package com.vdudnyk.appointmentengine.application.businessflow.shared;

import lombok.Data;

@Data
public class CreateUserAndSalonRequest {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private boolean tacAcceptance;
    private String salonName;
}
