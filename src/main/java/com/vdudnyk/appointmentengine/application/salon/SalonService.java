package com.vdudnyk.appointmentengine.application.salon;

import com.vdudnyk.appointmentengine.application.salon.service.ServiceType;
import com.vdudnyk.appointmentengine.application.salon.shared.AddServiceTypeRequest;
import com.vdudnyk.appointmentengine.application.salon.shared.AddWorkerToSalonRequest;
import com.vdudnyk.appointmentengine.application.salon.shared.CreateSalonRequest;
import com.vdudnyk.appointmentengine.application.salon.shared.SalonResponse;
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
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
class SalonService {
    private final SalonRepository salonRepository;
    private final UserFacade userFacade;
    private final UserAuthentication userAuthentication;
    private final ServiceTypeRepository serviceTypeRepository;

    SalonResponse createSalon(CreateSalonRequest createSalonRequest) {
        User authenticatedUser = userAuthentication.getAuthenticatedUser();
        Salon salon = new Salon();
        salon.setName(createSalonRequest.getName());
        salon.setOwner(authenticatedUser);
        salon.setDefaultCurrency(Currency.getInstance("PLN"));
        salon.setServices(new ArrayList<>());
        return toSalonResponse(salonRepository.save(salon));
    }

    List<SalonResponse> addServiceType(AddServiceTypeRequest addServiceTypeRequest) {
        User authenticatedUser = userAuthentication.getAuthenticatedUser();
        List<Salon> allByOwner = salonRepository.findAllByOwner(authenticatedUser);
        allByOwner
                .stream()
                .filter(salon -> salon.getId().equals(addServiceTypeRequest.getSalonId()))
                .findFirst()
                .ifPresent(salon -> {
                    ServiceType serviceType = new ServiceType()
                            .withName(addServiceTypeRequest.getServiceName())
                            .withPrice(addServiceTypeRequest.getPrice());
                    salon.getServices().add(serviceTypeRepository.save(serviceType));

                    salonRepository.save(salon);
                });

        return salonRepository.findAllByOwner(authenticatedUser).stream().map(this::toSalonResponse).collect(Collectors.toList());
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

    List<SalonResponse> getUserSalons() {
        User authenticatedUser = userAuthentication.getAuthenticatedUser();
        return salonRepository.findAllByOwner(authenticatedUser)
                              .stream()
                              .map(this::toSalonResponse)
                              .collect(Collectors.toList());
    }

    private SalonResponse toSalonResponse(Salon salon) {
        return new SalonResponse()
                .withDefaultCurrency(salon.getDefaultCurrency())
                .withId(salon.getId())
                .withName(salon.getName())
                .withServices(salon.getServices());
    }
}
