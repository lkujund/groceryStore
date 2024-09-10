package hr.grocery.store.grocerystore.service;

import hr.grocery.store.grocerystore.model.*;
import hr.grocery.store.grocerystore.repository.SpringDataJpaGroceryRepository;
import hr.grocery.store.grocerystore.repository.SpringDataJpaOrderGroceryRepository;
import hr.grocery.store.grocerystore.repository.SpringDataJpaOrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private ShoppingCart shoppingCart;
    private SpringDataJpaGroceryRepository groceryRepository;
    private SpringDataJpaOrderRepository orderRepository;
    private SpringDataJpaOrderGroceryRepository orderGroceryRepository;


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

    @Override
    public void changeGroceryAmount(int id, int amount) {
        CartGrocery cartGrocery = shoppingCart.getShoppingCart().stream().filter(cg -> cg.getGrocery().getId() == id).findFirst().get();
        cartGrocery.setAmount(amount);
    }

    @Override
    public void removeGrocery(int id) {
        CartGrocery cartGrocery = shoppingCart.getShoppingCart().stream().filter(cg -> cg.getGrocery().getId() == id).findFirst().get();
        cartGrocery.setAmount(0);
        shoppingCart.getShoppingCart().remove(cartGrocery);
    }

    @Override
    public void clearShoppingCart() {
        shoppingCart.getShoppingCart().clear();
    }

    @Override
    public void pay(String paymentMethod) {
        User user = userDetailsServiceImpl.loadCurrentUser();

        Order order = new Order();
        order.setUser(user);
        order.setPaymentMethod(paymentMethod);
        order.setOrderTs(new Date());

        order.setGroceries(new ArrayList<OrderGrocery>());
        orderRepository.save(order);

        shoppingCart.getShoppingCart().forEach(cartGrocery -> {
            OrderGrocery og = new OrderGrocery();
            og.setOrder(order);
            og.setGrocery(cartGrocery.getGrocery());
            og.setCount(cartGrocery.getAmount());
            order.getGroceries().add(og);

            orderGroceryRepository.save(og);
        });

        shoppingCart.getShoppingCart().clear();
    }
}
