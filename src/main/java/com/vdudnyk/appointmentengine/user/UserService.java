package com.vdudnyk.appointmentengine.user;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    User addUser(AddUserRequest addUserRequest) {
        User user = new User();
        user.setUsername(addUserRequest.getUsername());

        String encodedPassword = passwordEncoder.encode(addUserRequest.getPassword());
        user.setPassword(encodedPassword);

        Role roleUser = roleRepository.getRoleByName("ROLE_USER");
        user.setRoles(asSet(roleUser));

        return userRepository.save(user);
    }

    List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Optional<User> authenticatedUser = userRepository.findByUsername(currentPrincipalName);
        return authenticatedUser.orElseThrow(() -> new UsernameNotFoundException("user not found"));
    }

    public Optional<User> getUserById(Long id){
        return userRepository.findById(id);
    }
}
