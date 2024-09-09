package hr.grocery.store.grocerystore.controller;

import hr.grocery.store.grocerystore.model.GroceryCategoryEnum;
import hr.grocery.store.grocerystore.model.GrocerySearchForm;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RootController {
    @GetMapping("")
    public String redirectToHome(Model model)
    {
        //todo: if admin redirect:admin/manageGroceries else redirect:store/grocerySearch
        return "redirect:store/grocerySearch";
    }
}
