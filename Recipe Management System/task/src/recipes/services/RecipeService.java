package recipes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import recipes.exceptions.RecipeNotFoundException;
import recipes.exceptions.RecipeNotOwnedByUserException;
import recipes.models.Recipe;
import recipes.repositories.RecipeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {

    @Autowired
    RecipeRepository repo;

    public List<Recipe> findAll() {
        List<Recipe> recipes = new ArrayList<>();
        repo.findAll().forEach(recipes::add);
        return recipes;
    }

    public Recipe findById(Long id) {
        return repo.findById(id).orElseThrow(RecipeNotFoundException::new);
    }

    public List<Recipe> findByName(String name) {
        return repo.findByNameContainingIgnoreCaseOrderByDateDesc(name);
    }

    public List<Recipe> findByCategory(String category) {
        return repo.findByCategoryIgnoreCaseOrderByDateDesc(category);
    }

    public long save(Recipe recipe) {
        Recipe saved = repo.save(recipe);
        return saved.getId();
    }

    public void checkAuthorshipAndDelete(long id, String email) {
        findRecipeAndCheckAuthor(id, email);
        repo.deleteById(id);
    }

    public void checkAuthorshipAndUpdate(long id, Recipe recipe, String email) {
        Recipe oldRecipe = findRecipeAndCheckAuthor(id, email);
        oldRecipe.setName(recipe.getName());
        oldRecipe.setDescription(recipe.getDescription());
        oldRecipe.setIngredients(recipe.getIngredients());
        oldRecipe.setDirections(recipe.getDirections());
        oldRecipe.setCategory(recipe.getCategory());

        repo.save(oldRecipe);
    }

    private Recipe findRecipeAndCheckAuthor(long id, String email) {
        Optional<Recipe> recipe = repo.findById(id);
        if (recipe.isEmpty()) {
            throw new RecipeNotFoundException();
        }
        if (!recipe.get().getAuthor().equals(email)) {
            throw new RecipeNotOwnedByUserException("Recipe with id %d is not owned by user %s".formatted(id, email));
        }
        return recipe.get();
    }
}
