package recipes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import recipes.exceptions.RecipeNotFoundException;
import recipes.models.Recipe;
import recipes.repositories.RecipeRepository;

import javax.validation.Valid;
import java.util.List;

@Service
public class RecipeService {

    @Autowired
    RecipeRepository repo;

    public Recipe findById(Long id) {
        return repo.findById(id).orElseThrow(RecipeNotFoundException::new);
    }

    public List<Recipe> findByName(String name) {
        return repo.findByNameContainingIgnoreCaseOrderByDateDesc(name);
    }

    public List<Recipe> findByCategory(String category) {
        return repo.findByCategoryIgnoreCaseOrderByDateDesc(category);
    }

    public long save(@Valid @RequestBody Recipe recipe) {
        Recipe saved = repo.save(recipe);
        return saved.getId();
    }

    public void deleteById(long id) {
        if (!repo.existsById(id)) {
            throw new RecipeNotFoundException();
        }
        repo.deleteById(id);
    }

    public void update(long id, Recipe recipe) {
        Recipe oldRecipe = findById(id);
        oldRecipe.setName(recipe.getName());
        oldRecipe.setDescription(recipe.getDescription());
        oldRecipe.setIngredients(recipe.getIngredients());
        oldRecipe.setDirections(recipe.getDirections());
        oldRecipe.setCategory(recipe.getCategory());

        repo.save(oldRecipe);
    }
}
