package com.vdudnyk.appointmentengine.infrastructure.controller;

import com.vdudnyk.appointmentengine.application.user.User;
import com.vdudnyk.appointmentengine.application.user.UserFacade;
import com.vdudnyk.appointmentengine.application.user.shared.AddUserRequest;
import com.vdudnyk.appointmentengine.application.user.shared.SignInRequest;
import com.vdudnyk.appointmentengine.application.user.shared.SignUpRequest;
import com.vdudnyk.appointmentengine.application.user.shared.TokenResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.annotation.security.RolesAllowed;
import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("users")
public class UserController {
    private final UserFacade userFacade;

    @PostMapping
    @RolesAllowed("ROLE_OWNER")
    public ResponseEntity<User> addUser(@RequestBody AddUserRequest addUserRequest) {

        User user = userFacade.addUser(addUserRequest);

        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> allUsers = userFacade.getAllUsers();

        return ResponseEntity.ok(allUsers);
    }

    @PostMapping("/signup")
    public ResponseEntity<User> signUpAsBusinessOwner(@RequestBody SignUpRequest signUpRequest) {
        User user = userFacade.signUpAsBusinessOwner(signUpRequest);
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{id}")
                .buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(location).body(user);
    }

    @PostMapping("/signin")
    public ResponseEntity<TokenResponse> signIn(@RequestBody SignInRequest signInRequest) {
        return ResponseEntity.ok(userFacade.authenticateUser(signInRequest));
    }
}
