package hr.grocery.store.grocerystore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroceryDTO {
    private String name;
    private String categoryString;
    private String measuringUnitString;
    private BigDecimal measure;
    private BigDecimal price;
    private String description;
    private byte[] image;
}
