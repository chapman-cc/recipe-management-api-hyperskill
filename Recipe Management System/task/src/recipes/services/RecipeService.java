package recipes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import recipes.exceptions.RecipeNotFoundException;
import recipes.models.Recipe;
import recipes.repositories.RecipeRepository;

@Service
public class RecipeService {

    @Autowired
    RecipeRepository recipeRepository;

    public Recipe findById(Long id) {
        return recipeRepository.findById(id).orElseThrow(RecipeNotFoundException::new);
    }

    public long save(Recipe recipe) {
        Recipe saved = recipeRepository.save(recipe);
        return saved.getId();
    }
}
