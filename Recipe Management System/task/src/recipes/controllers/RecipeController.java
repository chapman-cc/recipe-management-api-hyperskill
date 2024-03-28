package recipes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import recipes.models.Recipe;
import recipes.services.RecipeService;

import javax.validation.Valid;
import java.util.Map;

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
    public Object createRecipe(@Valid @RequestBody Recipe recipe) {
        long id = service.save(recipe);
        return Map.of("id", id);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteRecipe(@PathVariable long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
