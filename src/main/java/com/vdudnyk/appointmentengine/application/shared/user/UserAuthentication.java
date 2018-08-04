package com.vdudnyk.appointmentengine.application.shared.user;

import com.vdudnyk.appointmentengine.application.shared.exception.UserException;
import com.vdudnyk.appointmentengine.application.user.User;
import com.vdudnyk.appointmentengine.application.user.UserFacade;
import com.vdudnyk.appointmentengine.infrastructure.config.UserPrincipal;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserAuthentication {
    private final UserFacade userFacade;

    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        Optional<User> userByUsername = userFacade.getUserByUsername(userPrincipal.getUsername());

        return userByUsername.orElseThrow(() -> new UserException("User not found"));
    }
}
