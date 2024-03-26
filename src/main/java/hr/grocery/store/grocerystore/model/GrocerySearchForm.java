package hr.grocery.store.grocerystore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GrocerySearchForm {
    private String name;
    private String category;
    private BigDecimal lowerPrice;
    private BigDecimal upperPrice;
    private String description;
}
