package hr.grocery.store.grocerystore.event;

import hr.grocery.store.grocerystore.model.CartGrocery;
import hr.grocery.store.grocerystore.model.Grocery;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Setter
@Getter
public class AddToCartEvent  extends ApplicationEvent
{
    private CartGrocery cartGrocery;

    public AddToCartEvent(Object source, CartGrocery cartGrocery) {
        super(source);
        this.cartGrocery = cartGrocery;
    }
}
