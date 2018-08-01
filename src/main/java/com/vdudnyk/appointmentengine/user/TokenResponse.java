package com.vdudnyk.appointmentengine.user;

import lombok.Data;

@Data
class TokenResponse {
    private String token;
    private String tokenType;

    TokenResponse(String token) {
        this.token = token;
        this.tokenType = "Bearer";
    }

}
