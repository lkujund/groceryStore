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
public class GroceryDTO {

    @Size(min = 3, max = 30, message = "The name of a grocery must be between {min} and {max} characters.")
    private String name;

    @NotEmpty(message = "Category is required.")
    private String categoryString;

    @NotEmpty(message = "Unit of measure is required.")
    private String measuringUnitString;

    @NotNull(message = "The measure value is required.")
    @DecimalMin(value = "0.1", message = "The measure must be a positive non-zero number.")
    private BigDecimal measure;

    @NotNull(message = "The measure value is required.")
    @DecimalMin(value = "0.1", message = "The price must be a positive non-zero number.")
    private BigDecimal price;

    @NotEmpty(message = "Description is required.")
    private String description;
    private byte[] image;
}
