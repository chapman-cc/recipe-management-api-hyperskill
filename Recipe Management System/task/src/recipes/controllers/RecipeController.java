package recipes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import recipes.models.Recipe;
import recipes.services.RecipeService;

import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping(value = "/api/recipe")
public class RecipeController {

    @Autowired
    private RecipeService service;

    @GetMapping(path = "/{id}")
    public Recipe getRecipe(@PathVariable long id) {
        return service.findById(id);
    }

    @PostMapping(path = "/new")
    @ResponseStatus(code = HttpStatus.OK)
    public Object createRecipe(@RequestBody Recipe recipe) {
        long id = service.save(recipe);
        return Map.of("id", id);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteRecipe(@PathVariable long id) {
        service.deleteById(id);
    }
}
