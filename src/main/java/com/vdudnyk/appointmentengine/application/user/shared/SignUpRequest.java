package com.vdudnyk.appointmentengine.application.user.shared;

import lombok.Data;

@Data
public class SignUpRequest {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private boolean tacAcceptance;
}
