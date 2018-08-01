package com.vdudnyk.appointmentengine.user;

import lombok.Data;

@Data
class SignUpRequest {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private boolean tacAcceptance;
}
