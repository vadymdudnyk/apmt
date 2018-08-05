package com.vdudnyk.appointmentengine.application.user.shared;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterUserRequest {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private boolean tacAcceptance;
}
