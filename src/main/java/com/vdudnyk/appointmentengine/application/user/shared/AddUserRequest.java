package com.vdudnyk.appointmentengine.application.user.shared;

import lombok.Data;

@Data
public class AddUserRequest {
    private String username;
    private String password;
}
