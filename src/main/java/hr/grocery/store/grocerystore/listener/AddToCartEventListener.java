package hr.grocery.store.grocerystore.listener;

import hr.grocery.store.grocerystore.event.AddToCartEvent;
import hr.grocery.store.grocerystore.service.ShoppingCartService;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AddToCartEventListener implements ApplicationListener<AddToCartEvent> {

    private ShoppingCartService shoppingCartService;


    @Override
    public void onApplicationEvent(AddToCartEvent event) {
        System.out.println("Received spring custom event - " + event.getCartGrocery());
        shoppingCartService.addItemToCart(event.getCartGrocery());
    }
}
