package com.vdudnyk.appointmentengine.application.user.shared;

import lombok.Data;

@Data
public class TokenResponse {
    private String token;
    private String tokenType;

    public TokenResponse(String token) {
        this.token = token;
        this.tokenType = "Bearer";
    }

}
