package hr.grocery.store.grocerystore.service;

import hr.grocery.store.grocerystore.model.Order;

import java.util.List;

public interface OrderService {
    List<Order> getOrders();
    List<Order> getOrdersByUser(String username);
    List<Order> getOrdersByDate(String date);
}
