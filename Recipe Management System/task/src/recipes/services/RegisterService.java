package recipes.services;

import org.springframework.stereotype.Service;
import recipes.models.RecipeUser;
import recipes.repositories.RecipeUserRepository;

@Service
public class RegisterService {

    private final RecipeUserRepository repository;

    public RegisterService(RecipeUserRepository repository) {
        this.repository = repository;
    }

    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    public void register(String email, String password) {
        RecipeUser user = new RecipeUser();
        user.setEmail(email);
        // TODO: add password encoder
        user.setPassword(password);
        repository.save(user);
    }
}
