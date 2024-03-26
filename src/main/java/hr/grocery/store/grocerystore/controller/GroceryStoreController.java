package hr.grocery.store.grocerystore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/store")
public class GroceryStoreController {


    @PostMapping("/search")
    public String searchGroceries() {
        return ("redirect:store/search");
    }

    @GetMapping("/grocery/{id}")
    public String getGroceryDetails(@PathVariable Integer id) {

        return ("store/grocery");
    }
}
