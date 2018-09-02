package com.vdudnyk.appointmentengine.application.client;

import com.vdudnyk.appointmentengine.application.client.shared.AddClientRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClientFacade {
    private final ClientService clientService;

    public Client addClient(AddClientRequest createClientRequest) {
        return clientService.createClient(createClientRequest);
    }

    public List<Client> getAllSalonClients() {
        return clientService.getAllSalonClients();
    }

    public void updateClient() {

    }

    public void deleteClient() {

    }

    public Client getClient(Long clientId) {
        return clientService.getClient(clientId);
    }
}
