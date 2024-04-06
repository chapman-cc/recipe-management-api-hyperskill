package recipes.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Recipe {
    @Id
    @GeneratedValue
    @JsonIgnore
    private Long id;

    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @ElementCollection
    @Size(min = 1)
    private List<String> ingredients = new ArrayList<>();
    @ElementCollection
    @Size(min = 1)
    private List<String> directions = new ArrayList<>();
    @NotBlank
    private String category;
    @UpdateTimestamp
    private LocalDateTime date;
    @Email
    @JsonIgnore
    private String author;

    public Recipe(String name, String description, List<String> ingredients, List<String> directions, String category, String author) {
        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
        this.directions = directions;
        this.category = category;
        this.author = author;
    }
}
