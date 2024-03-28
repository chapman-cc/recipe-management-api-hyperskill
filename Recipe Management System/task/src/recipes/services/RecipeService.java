package recipes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import recipes.exceptions.RecipeNotFoundException;
import recipes.models.Recipe;
import recipes.repositories.RecipeRepository;

import javax.validation.Valid;

@Service
public class RecipeService {

    @Autowired
    RecipeRepository repo;

    public Recipe findById(Long id) {
        return repo.findById(id).orElseThrow(RecipeNotFoundException::new);
    }

    public long save(@Valid @RequestBody Recipe recipe) {
        Recipe saved = repo.save(recipe);
        return saved.getId();
    }

    public void deleteById(long id) {
        repo.findById(id).orElseThrow(RecipeNotFoundException::new);
        repo.deleteById(id);
    }
}
