package recipes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import recipes.models.RecipeUser;
import recipes.repositories.RecipeUserRepository;

@Service
public class RegisterService {

    private final RecipeUserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegisterService(RecipeUserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    public void register(String email, String password) {
        String encodedPassword = passwordEncoder.encode(password);
        RecipeUser user = new RecipeUser();
        user.setEmail(email);
        user.setPassword(encodedPassword);
        repository.save(user);
    }
}
