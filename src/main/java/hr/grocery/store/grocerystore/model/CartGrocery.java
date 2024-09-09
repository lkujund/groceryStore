package hr.grocery.store.grocerystore.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartGrocery {
    private Grocery grocery;
    private Integer amount;
}
