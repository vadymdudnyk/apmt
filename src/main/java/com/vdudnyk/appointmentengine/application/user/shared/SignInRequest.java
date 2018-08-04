package com.vdudnyk.appointmentengine.application.user.shared;

import lombok.Data;

@Data
public class SignInRequest {
    private String username;
    private String password;
}
