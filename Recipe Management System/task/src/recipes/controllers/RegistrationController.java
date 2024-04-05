package recipes.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import recipes.exceptions.UserExistsException;
import recipes.models.RecipeUser;
import recipes.services.RegisterService;

import javax.validation.Valid;

@RestController
public class RegistrationController {
    private final RegisterService service;

    public RegistrationController(RegisterService service) {
        this.service = service;
    }

    @PostMapping("/api/register")
    public String register(@Valid @RequestBody RecipeUser registration) {
        if (service.existsByEmail(registration.getEmail())) {
            throw new UserExistsException("User %s already exists".formatted(registration.getEmail()));
        }

        service.register(registration.getEmail(), registration.getPassword());
        return "user \"%s\" registered".formatted(registration.getEmail());
    }
}
