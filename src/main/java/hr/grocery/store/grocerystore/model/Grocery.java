package hr.grocery.store.grocerystore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Grocery {
    private Integer id;
    private String name;
    private GroceryCategory category;
    private MeasuringUnit measuringUnit;
    private BigDecimal measure;
    private BigDecimal price;
    private String description;
    private byte[] image;
}
