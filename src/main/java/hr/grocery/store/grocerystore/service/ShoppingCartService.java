package hr.grocery.store.grocerystore.service;

import hr.grocery.store.grocerystore.model.CartGrocery;
import hr.grocery.store.grocerystore.model.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {
    void addItemToCart(CartGrocery cartGrocery);
    List<CartGrocery> getCartGroceryList();
}
