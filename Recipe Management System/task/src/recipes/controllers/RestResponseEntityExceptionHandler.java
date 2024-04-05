package recipes.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import recipes.exceptions.RecipeNotFoundException;
import recipes.exceptions.UserExistsException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RecipeNotFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ResponseEntity<Void> handleRecipeNotFoundException() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(UserExistsException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleUserExistsException(Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
