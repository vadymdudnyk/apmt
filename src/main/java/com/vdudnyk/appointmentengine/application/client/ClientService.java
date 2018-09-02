package com.vdudnyk.appointmentengine.application.client;

import com.vdudnyk.appointmentengine.application.client.shared.AddClientRequest;
import com.vdudnyk.appointmentengine.application.salon.SalonFacade;
import com.vdudnyk.appointmentengine.application.salon.shared.SalonResponse;
import com.vdudnyk.appointmentengine.application.shared.exception.ApiException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
class ClientService {
    private final ClientRepository clientRepository;
    private final SalonFacade salonFacade;

    Client createClient(AddClientRequest createClientRequest) {
        if (createClientRequest.getFirstName() == null) {
            throw new ApiException("FirstName cannot be null");
        }

        SalonResponse userSalon = salonFacade.getUserSalon();
        if (createClientRequest.getId() != null) {
            Optional<Client> possibleClient = clientRepository.findByIdAndSalonId(createClientRequest.getId(), userSalon.getId());
            if (possibleClient.isPresent()) {
                return possibleClient.get();
            }
        }

        Client client = new Client();
        client.setFirstName(createClientRequest.getFirstName());
        client.setLastName(createClientRequest.getLastName());
        client.setPhoneNumber(createClientRequest.getPhoneNumber());
        client.setSocialMediaLink(createClientRequest.getSocialLink());
        client.setSalonId(userSalon.getId());

        return clientRepository.save(client);
    }

    void updateClient() {

    }

    void deleteClient() {

    }

    Client getClient(Long clientId) {
        SalonResponse userSalon = salonFacade.getUserSalon();
        return clientRepository.findByIdAndSalonId(clientId, userSalon.getId()).orElseThrow(() -> new ApiException("Client not found"));
    }

    List<Client> getAllSalonClients() {
        SalonResponse userSalon = salonFacade.getUserSalon();
        return clientRepository.findAllBySalonId(userSalon.getId());
    }
}
