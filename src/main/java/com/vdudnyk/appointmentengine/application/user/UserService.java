package com.vdudnyk.appointmentengine.application.user;

import com.vdudnyk.appointmentengine.application.shared.exception.ApiException;
import com.vdudnyk.appointmentengine.application.user.shared.AddUserRequest;
import com.vdudnyk.appointmentengine.application.user.shared.SignInRequest;
import com.vdudnyk.appointmentengine.application.user.shared.SignUpRequest;
import com.vdudnyk.appointmentengine.application.user.shared.TokenResponse;
import com.vdudnyk.appointmentengine.infrastructure.config.JwtTokenProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.CollectionHelper.asSet;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;

    User addUser(AddUserRequest addUserRequest) {
        User user = new User();
        user.setUsername(addUserRequest.getUsername());

        String encodedPassword = passwordEncoder.encode(addUserRequest.getPassword());
        user.setPassword(encodedPassword);

        Role roleUser = roleRepository.getRoleByName("ROLE_USER");
        user.setRoles(asSet(roleUser));

        return userRepository.save(user);
    }

    User signUpAsBusinessOwner(SignUpRequest signUpRequest) {
        Optional<User> optionalUser = userRepository.findByUsername(signUpRequest.getUsername());
        if (optionalUser.isPresent()) {
            throw new ApiException("Username already taken");
        }

        User user = new User();
        user.setUsername(signUpRequest.getUsername());
        user.setFirstName(signUpRequest.getFirstName());
        user.setLastName(signUpRequest.getLastName());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setRoles(asSet(roleRepository.getRoleByName("ROLE_OWNER")));

        return userRepository.save(user);
    }

    TokenResponse authenticateUser(SignInRequest signInRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        signInRequest.getUsername(),
                        signInRequest.getPassword()
                )
                                                                          );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwtToken = tokenProvider.generateToken(authentication);
        return new TokenResponse(jwtToken);
    }

    List<User> getAllUsers() {
        return userRepository.findAll();
    }

    Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
}
