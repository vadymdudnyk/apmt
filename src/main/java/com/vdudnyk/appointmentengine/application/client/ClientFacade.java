package com.vdudnyk.appointmentengine.application.client;

import com.vdudnyk.appointmentengine.application.client.shared.CreateOrGetClientRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClientFacade {
    private final ClientService clientService;

    public Client createOrGetClient(CreateOrGetClientRequest createClientRequest) {
        return clientService.createClient(createClientRequest);
    }

    public List<Client> getAllSalonClients() {
        return clientService.getAllSalonClients();
    }
    public void updateClient() {

    }

    public void deleteClient() {

    }

    public void getClient(Long clientId) {

    }
}
