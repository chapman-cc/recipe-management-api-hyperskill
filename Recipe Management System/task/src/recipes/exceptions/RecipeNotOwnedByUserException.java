package recipes.exceptions;

public class RecipeNotOwnedByUserException extends RuntimeException {
    public RecipeNotOwnedByUserException(String message) {
        super(message);
    }
}
