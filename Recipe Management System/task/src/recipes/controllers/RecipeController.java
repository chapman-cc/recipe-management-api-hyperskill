package recipes.controllers;

import org.springframework.web.bind.annotation.*;
import recipes.models.Recipe;

@RestController
@RequestMapping(value = "/api/recipe")
public class RecipeController {

    private Recipe recipe;

    @GetMapping
    public Recipe getRecipe(){
        return recipe;
    }

    @PostMapping
    public void createRecipe(@RequestBody Recipe recipe) {
        this.recipe = recipe;
    }
}
