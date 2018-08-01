package com.vdudnyk.appointmentengine.user;

import lombok.Data;

@Data
public class SignInRequest {
    private String username;
    private String password;
}
