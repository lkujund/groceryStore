package hr.grocery.store.grocerystore.controller;

import hr.grocery.store.grocerystore.dto.GroceryDTO;
import hr.grocery.store.grocerystore.event.AddToCartEvent;
import hr.grocery.store.grocerystore.model.CartGrocery;
import hr.grocery.store.grocerystore.model.Grocery;
import hr.grocery.store.grocerystore.model.GroceryCategoryEnum;
import hr.grocery.store.grocerystore.model.MeasuringUnitEnum;
import hr.grocery.store.grocerystore.service.GroceryCategoryService;
import hr.grocery.store.grocerystore.service.GroceryService;
import hr.grocery.store.grocerystore.service.ShoppingCartService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/store")
@AllArgsConstructor
public class GroceryStoreController {
    private GroceryService groceryService;
    private ShoppingCartService shoppingCartService;
    private GroceryCategoryService groceryCategoryService;
    private ApplicationEventPublisher eventPublisher;

    @PostMapping("/addToCart")
    public String addToCart(@RequestParam("id") int id,
                            @RequestParam("amount") int amount)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Grocery grocery = groceryService.findByIdAdmin(id).get();

        CartGrocery cartGrocery = new CartGrocery();
        cartGrocery.setGrocery(grocery);
        cartGrocery.setAmount(amount);


        eventPublisher.publishEvent(new AddToCartEvent(this, cartGrocery));

        return "redirect:/store/shoppingCart";
    }

    @GetMapping("/shoppingCart")
    public String viewShoppingCart(Model model)
    {
        model.addAttribute("cartGroceries", shoppingCartService.getCartGroceryList());
        return "store/shoppingCart";
    }
}
