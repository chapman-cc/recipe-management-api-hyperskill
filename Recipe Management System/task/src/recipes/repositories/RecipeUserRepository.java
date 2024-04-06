package recipes.repositories;

import org.springframework.data.repository.CrudRepository;
import recipes.models.RecipeUser;

import java.util.Optional;

public interface RecipeUserRepository extends CrudRepository<RecipeUser, Long> {
    Optional<RecipeUser> findByEmail(String email);
    boolean existsByEmail(String email);
}
