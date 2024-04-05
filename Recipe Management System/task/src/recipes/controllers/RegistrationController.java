package recipes.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import recipes.exceptions.UserExistsException;
import recipes.models.RequestRegistration;
import recipes.services.RegisterService;

import javax.validation.Valid;

@RestController
public class RegistrationController {
    private final RegisterService service;

    public RegistrationController(RegisterService service) {
        this.service = service;
    }

    @PostMapping("/api/register")
    public String register(@Valid @RequestBody RequestRegistration registration) {
        if (service.existsByEmail(registration.email())) {
            throw new UserExistsException("User %s already exists".formatted(registration.email()));
        }

        service.register(registration.email(), registration.password());
        return "user %s registered".formatted(registration.email());
    }
}
