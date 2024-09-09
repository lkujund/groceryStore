package hr.grocery.store.grocerystore.event;

import hr.grocery.store.grocerystore.model.Grocery;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Setter
@Getter
public class AddToCartEvent  extends ApplicationEvent
{
    private Grocery grocery;

    public AddToCartEvent(Object source, Grocery grocery) {
        super(source);
        this.grocery = grocery;
    }
}
