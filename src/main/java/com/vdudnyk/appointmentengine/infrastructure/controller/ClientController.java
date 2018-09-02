package com.vdudnyk.appointmentengine.infrastructure.controller;

import com.vdudnyk.appointmentengine.application.client.Client;
import com.vdudnyk.appointmentengine.application.client.ClientFacade;
import com.vdudnyk.appointmentengine.application.client.shared.AddClientRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("salon/clients")
@AllArgsConstructor
public class ClientController {
    private final ClientFacade clientFacade;

    @PostMapping
    public ResponseEntity<Client> addNewClient(@RequestBody AddClientRequest addClientRequest) {
        Client client = clientFacade.addClient(addClientRequest);
        return ResponseEntity.ok(client);
    }

    @GetMapping
    public ResponseEntity<List<Client>> getAllClients() {
        List<Client> allSalonClients = clientFacade.getAllSalonClients();
        return ResponseEntity.ok(allSalonClients);
    }

}
