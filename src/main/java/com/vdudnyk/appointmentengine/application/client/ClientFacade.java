package com.vdudnyk.appointmentengine.application.client;

import com.vdudnyk.appointmentengine.application.client.shared.CreateOrGetClientRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClientFacade {
    private final ClientService clientService;

    public Client createOrGetClient(CreateOrGetClientRequest createClientRequest) {
        return clientService.createClient(createClientRequest);
    }

    public void updateClient() {

    }

    public void deleteClient() {

    }

    public void getClient(Long clientId) {

    }
}
