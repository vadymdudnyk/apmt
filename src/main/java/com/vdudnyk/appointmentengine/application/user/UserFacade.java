package com.vdudnyk.appointmentengine.application.user;

import com.vdudnyk.appointmentengine.application.user.shared.AddUserRequest;
import com.vdudnyk.appointmentengine.application.user.shared.SignInRequest;
import com.vdudnyk.appointmentengine.application.user.shared.SignUpRequest;
import com.vdudnyk.appointmentengine.application.user.shared.TokenResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserFacade {
    private final UserService userService;

    public User addUser(AddUserRequest addUserRequest) {
        return userService.addUser(addUserRequest);
    }

    public User signUpAsBusinessOwner(SignUpRequest signUpRequest) {
        return userService.signUpAsBusinessOwner(signUpRequest);
    }

    public TokenResponse authenticateUser(SignInRequest signInRequest) {
        return userService.authenticateUser(signInRequest);
    }

    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    public Optional<User> getUserByUsername(String username) {
        return userService.getUserByUsername(username);
    }

    public Optional<User> getUserById(Long id) {
        return userService.getUserById(id);
    }
}
