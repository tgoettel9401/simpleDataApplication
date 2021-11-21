package com.example.simpledataapplication.controller;

import com.example.simpledataapplication.controller.transferObjects.RegistrationRequest;
import com.example.simpledataapplication.model.User;
import com.example.simpledataapplication.repository.UserRepository;
import com.example.simpledataapplication.services.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("/register")
    public String showRegister() {
        return "register";
    }

    @PostMapping("/register")
    public RedirectView saveRegister(@ModelAttribute("user") RegistrationRequest registrationRequest) {
        User user = userService.registerUser(registrationRequest);
        return new RedirectView("/users");
    }

    @GetMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String index(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "users";
    }

}
