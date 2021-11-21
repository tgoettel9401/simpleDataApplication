package com.example.simpledataapplication.config;

import com.example.simpledataapplication.model.Role;
import com.example.simpledataapplication.model.User;
import com.example.simpledataapplication.repository.RoleRepository;
import com.example.simpledataapplication.repository.UserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class UserDataInitializer implements ApplicationRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserDataInitializer(UserRepository userRepository, RoleRepository roleRepository,
                               PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(ApplicationArguments args) {

        Role adminRole = new Role();
        adminRole.setName("ROLE_ADMIN");
        adminRole.setAdmin(true);
        adminRole = roleRepository.save(adminRole);

        User user = new User();
        user.setFirstName("Admin");
        user.setLastName("User");
        user.setPassword(passwordEncoder.encode("secret"));
        user.setUsername("admin");
        user.setEmail("admin@data.com");
        user.setRoles(Arrays.asList(adminRole));
        userRepository.save(user);
    }

}
