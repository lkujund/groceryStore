package hr.grocery.store.grocerystore.service;

import hr.grocery.store.grocerystore.model.Order;
import hr.grocery.store.grocerystore.model.OrderSearchForm;

import java.util.List;

public interface OrderService {
    List<Order> getOrders();
    List<Order> getOrdersByUser(String username);

    List<Order> filterByCriteria(OrderSearchForm orderSearchForm);
}
