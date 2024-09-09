package hr.grocery.store.grocerystore.controller.rest;

import hr.grocery.store.grocerystore.dto.GroceryDTO;
import hr.grocery.store.grocerystore.model.Order;
import hr.grocery.store.grocerystore.service.GroceryService;
import hr.grocery.store.grocerystore.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/rest")
@AllArgsConstructor
public class OrderRestController {


    private OrderService orderService;

    @GetMapping("/order/name/{name}")
    public ResponseEntity<List<Order>> getOrderByName(@PathVariable String name) {
        return new ResponseEntity<>(orderService.getOrdersByUser(name), HttpStatus.OK);
    }

    @GetMapping("/order/date/{date}")
    public ResponseEntity<List<Order>> getOrderByDate(@PathVariable String date) {
        return new ResponseEntity<>(orderService.getOrdersByDate(date), HttpStatus.OK);
    }
}
