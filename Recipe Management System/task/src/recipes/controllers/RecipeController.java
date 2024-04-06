package recipes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import recipes.models.Recipe;
import recipes.services.RecipeService;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/recipe")
public class RecipeController {

    @Autowired
    private RecipeService service;

    @GetMapping
    public List<Recipe> getRecipes() {
        return service.findAll();
    }

    @GetMapping(path = "/{id}")
    public Recipe getRecipe(@PathVariable long id) {
        return service.findById(id);
    }

    @GetMapping(path = "/search", params = "name")
    @ResponseStatus(code = HttpStatus.OK)
    public List<Recipe> searchRecipeByName(@RequestParam String name) {
        return service.findByName(name);
    }

    @GetMapping(path = "/search", params = "category")
    @ResponseStatus(code = HttpStatus.OK)
    public List<Recipe> searchRecipeByCategory(@RequestParam String category) {
        return service.findByCategory(category);
    }

    @PostMapping(path = "/new")
    @ResponseStatus(code = HttpStatus.OK)
    public Object createRecipe(@Valid @RequestBody Recipe recipe, @AuthenticationPrincipal UserDetails details) {
        recipe.setAuthor(details.getUsername());
        long id = service.save(recipe);
        return Map.of("id", id);
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> updateRecipe(@PathVariable long id, @Valid @RequestBody Recipe recipe, @AuthenticationPrincipal UserDetails details) {
        service.checkAuthorshipAndUpdate(id, recipe, details.getUsername());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteRecipe(@PathVariable long id, @AuthenticationPrincipal UserDetails details) {
        service.checkAuthorshipAndDelete(id, details.getUsername());
        return ResponseEntity.noContent().build();
    }
}
