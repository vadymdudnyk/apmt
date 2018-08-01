package com.vdudnyk.appointmentengine.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.annotation.security.RolesAllowed;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("users")
@Slf4j
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @RolesAllowed("ROLE_OWNER")
    public ResponseEntity<User> addUser(@RequestBody AddUserRequest addUserRequest) {

        User user = userService.addUser(addUserRequest);

        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> allUsers = userService.getAllUsers();

        return ResponseEntity.ok(allUsers);
    }

    @PostMapping("/signup")
    public ResponseEntity<User> signUpAsBusinessOwner(@RequestBody SignUpRequest signUpRequest) {
        User user = userService.signUpAsBusinessOwner(signUpRequest);
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{id}")
                .buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(location).body(user);
    }

    @PostMapping("/signin")
    public ResponseEntity<TokenResponse> signIn(@RequestBody SignInRequest signInRequest) {
        return ResponseEntity.ok(userService.authenticateUser(signInRequest));
    }
}
