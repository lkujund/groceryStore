package hr.grocery.store.grocerystore.controller.rest;

import hr.grocery.store.grocerystore.model.Grocery;
import hr.grocery.store.grocerystore.service.GroceryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/rest")
@AllArgsConstructor
public class GroceryStoreRestController {


    private GroceryService groceryService;

    @GetMapping("/grocery/name/{name}")
    public ResponseEntity<List<Grocery>> getGroceryByName(@PathVariable String name) {
        return new ResponseEntity<>(groceryService.findByNameAdmin(name), HttpStatus.OK);
    }
}
