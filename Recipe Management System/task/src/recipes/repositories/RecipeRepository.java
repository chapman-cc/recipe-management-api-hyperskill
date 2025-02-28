package recipes.repositories;

import org.springframework.data.repository.CrudRepository;
import recipes.models.Recipe;

import java.util.List;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
    List<Recipe> findByNameContainingIgnoreCaseOrderByDateDesc(String name);

    List<Recipe> findByCategoryIgnoreCaseOrderByDateDesc(String category);
}
