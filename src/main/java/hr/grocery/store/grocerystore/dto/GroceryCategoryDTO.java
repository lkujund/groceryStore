package hr.grocery.store.grocerystore.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroceryCategoryDTO {
    @Size(min = 3, max = 30, message = "The name of a grocery must be between {min} and {max} characters.")
    private String name;
}
