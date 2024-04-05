package recipes.repositories;

import org.springframework.data.repository.CrudRepository;
import recipes.models.RecipeUser;

public interface RecipeUserRepository extends CrudRepository<RecipeUser, Long> {
    boolean existsByEmail(String email);
}
