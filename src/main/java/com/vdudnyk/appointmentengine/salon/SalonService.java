package com.vdudnyk.appointmentengine.salon;

import com.vdudnyk.appointmentengine.user.User;
import com.vdudnyk.appointmentengine.user.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class SalonService {
    private final SalonRepository salonRepository;
    private final UserService userService;

    Salon createSalon(CreateSalonRequest createSalonRequest) {
        Salon salon = new Salon();
        salon.setName(createSalonRequest.getName());
        Optional<User> possibleOwner = userService.getUserById(createSalonRequest.getOwnerId());
        salon.setOwner(possibleOwner.orElseThrow(() -> new UsernameNotFoundException("Owner not found")));
        return salonRepository.save(salon);
    }

    Salon addWorkerToSalon(AddWorkerToSalonRequest addWorkerToSalonRequest) {
        return salonRepository.findById(addWorkerToSalonRequest.getSalonId())
                              .map(salon -> {
                                  salon.getWorkers()
                                       .add(userService.getUserById(addWorkerToSalonRequest.getWorkerId())
                                                       .orElseThrow(() -> new UsernameNotFoundException("Worker not found")));
                                  return salon;
                              })
                              .map(salonRepository::save)
                              .orElseThrow(() -> new RuntimeException("Salon not found"));
    }
}
