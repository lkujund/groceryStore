package hr.grocery.store.grocerystore.service;

import hr.grocery.store.grocerystore.model.CartGrocery;
import hr.grocery.store.grocerystore.model.ShoppingCart;
import hr.grocery.store.grocerystore.repository.SpringDataJpaGroceryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private ShoppingCart shoppingCart;
    private SpringDataJpaGroceryRepository groceryRepository;


    @Override
    public void addItemToCart(CartGrocery cartGrocery) {
        boolean isAlreadyPresent = false;

        for (CartGrocery cg : shoppingCart.getShoppingCart()) {
            if (Objects.equals(cg.getGrocery().getId(), cartGrocery.getGrocery().getId())) {
                isAlreadyPresent = true;
                break;
            }
        }

        if (isAlreadyPresent) {
            shoppingCart.getShoppingCart().forEach(cg -> {
                if (Objects.equals(cg.getGrocery().getId(), cartGrocery.getGrocery().getId())) {
                    cg.setAmount(cg.getAmount() + cartGrocery.getAmount());
                }
            });
        } else {
            shoppingCart.getShoppingCart().add(cartGrocery);
        }
    }

    @Override
    public List<CartGrocery> getCartGroceryList() {
        return shoppingCart.getShoppingCart();
    }
}
