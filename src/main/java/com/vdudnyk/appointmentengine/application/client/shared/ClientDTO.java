package com.vdudnyk.appointmentengine.application.client.shared;

import lombok.Value;

@Value
public class ClientDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String socialMediaLink;
}
