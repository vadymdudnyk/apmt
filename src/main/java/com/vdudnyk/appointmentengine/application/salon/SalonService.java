package com.vdudnyk.appointmentengine.application.salon;

import com.vdudnyk.appointmentengine.application.salon.service.ServiceType;
import com.vdudnyk.appointmentengine.application.salon.shared.*;
import com.vdudnyk.appointmentengine.application.shared.Status;
import com.vdudnyk.appointmentengine.application.shared.StatusResponse;
import com.vdudnyk.appointmentengine.application.shared.exception.SalonException;
import com.vdudnyk.appointmentengine.application.shared.user.UserAuthentication;
import com.vdudnyk.appointmentengine.application.user.User;
import com.vdudnyk.appointmentengine.application.user.UserFacade;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
class SalonService {
    private final SalonRepository salonRepository;
    private final UserFacade userFacade;
    private final UserAuthentication userAuthentication;
    private final ServiceTypeRepository serviceTypeRepository;

    SalonResponse createSalonForAuthenticatedUser(CreateSalonRequest createSalonRequest) {
        User authenticatedUser = userAuthentication.getAuthenticatedUser();
        Salon salon = new Salon();
        salon.setName(createSalonRequest.getName());
        salon.setOwner(authenticatedUser);
        salon.setDefaultCurrency(Currency.getInstance("PLN"));
        salon.setServices(new ArrayList<>());

        Salon savedSalon = salonRepository.save(salon);
        log.info("Creating salon: {} to user: {}", savedSalon.getId(), authenticatedUser.getUsername());
        return toSalonResponse(savedSalon);
    }

    SalonResponse createSalonForUser(CreateSalonRequest createSalonRequest, User user) {
        Salon salon = new Salon();
        salon.setName(createSalonRequest.getName());
        salon.setOwner(user);
        salon.setDefaultCurrency(Currency.getInstance("PLN"));
        salon.setServices(new ArrayList<>());

        Salon savedSalon = salonRepository.save(salon);
        log.info("Creating salon: {} to user: {}", savedSalon.getId(), user.getUsername());
        return toSalonResponse(savedSalon);
    }

    SalonResponse addServiceType(AddServiceTypeRequest addServiceTypeRequest) {

        User authenticatedUser = userAuthentication.getAuthenticatedUser();
        Optional<Salon> userSalon = salonRepository.findFirstByOwner(authenticatedUser);
        userSalon
                .ifPresent(salon -> {
                    ServiceType serviceType = new ServiceType()
                            .withName(addServiceTypeRequest.getServiceName())
                            .withPrice(addServiceTypeRequest.getPrice());
                    salon.getServices().add(serviceTypeRepository.save(serviceType));
                    log.info("User: {} added new service: {}, price: {}",
                             authenticatedUser.getUsername(),
                             serviceType.getName(),
                             serviceType.getPrice());
                    salonRepository.save(salon);
                });

        return userSalon.map(this::toSalonResponse).orElseThrow(() -> new SalonException("Salon not found"));
    }

    SalonResponse addWorkerToSalon(AddWorkerToSalonRequest addWorkerToSalonRequest) {
        return salonRepository.findById(addWorkerToSalonRequest.getSalonId())
                              .map(salon -> {
                                  salon.getWorkers()
                                       .add(userFacade.getUserById(addWorkerToSalonRequest.getWorkerId())
                                                      .orElseThrow(() -> new UsernameNotFoundException("Worker not found")));
                                  return salon;
                              })
                              .map(salonRepository::save)
                              .map(this::toSalonResponse)
                              .orElseThrow(() -> new RuntimeException("Salon not found"));
    }

    SalonResponse getUserSalon() {
        User authenticatedUser = userAuthentication.getAuthenticatedUser();
        return salonRepository.findFirstByOwner(authenticatedUser)
                              .map(this::toSalonResponse)
                              .orElseThrow(() -> new SalonException("Salon not found"));
    }

    StatusResponse deleteServiceType(Long id) {
        log.info("Delete serviceType request: {}", id);
        User authenticatedUser = userAuthentication.getAuthenticatedUser();
        salonRepository.findFirstByOwner(authenticatedUser)
                       .ifPresent(salon -> {
                           List<ServiceType> services = salon.getServices();
                           salon.setServices(services.stream().filter(serviceType -> !serviceType.getId().equals(id)).collect(Collectors.toList()));
                           salonRepository.save(salon);
                       });
        return new StatusResponse(Status.SUCCESS);
    }

    StatusResponse updateServiceType(UpdateServiceTypeRequest updateServiceTypeRequest) {
        User authenticatedUser = userAuthentication.getAuthenticatedUser();
        salonRepository.findFirstByOwner(authenticatedUser)
                       .map(salon -> salon.getServices()
                                          .stream()
                                          .filter(serviceType -> serviceType.getId()
                                                                            .equals(updateServiceTypeRequest.getId()))
                                          .findAny()
                                          .orElseThrow(() -> new SalonException("User hasn't provided service type")))
                       .ifPresent(serviceType -> {
                           serviceType.setPrice(updateServiceTypeRequest.getPrice());
                           serviceType.setName(updateServiceTypeRequest.getServiceName());
                           serviceTypeRepository.save(serviceType);
                       });
        return new StatusResponse(Status.SUCCESS);
    }

    private SalonResponse toSalonResponse(Salon salon) {
        return new SalonResponse()
                .withDefaultCurrency(salon.getDefaultCurrency())
                .withId(salon.getId())
                .withName(salon.getName())
                .withServices(salon.getServices());
    }

}
