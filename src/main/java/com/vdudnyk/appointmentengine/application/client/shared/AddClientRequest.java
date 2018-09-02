package com.vdudnyk.appointmentengine.application.client.shared;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddClientRequest {
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String socialLink;
}
