package hr.grocery.store.grocerystore.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@Component
public class ShoppingCart {
    private List<CartGrocery> shoppingCart = new ArrayList<>();

}
