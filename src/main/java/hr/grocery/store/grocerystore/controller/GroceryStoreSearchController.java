package hr.grocery.store.grocerystore.controller;

import hr.grocery.store.grocerystore.model.Grocery;
import hr.grocery.store.grocerystore.model.GroceryCategory;
import hr.grocery.store.grocerystore.model.GrocerySearchForm;
import hr.grocery.store.grocerystore.service.GroceryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.ArrayList;

@Controller
@RequestMapping("/store")
@AllArgsConstructor
@SessionAttributes({"groceries", "grocerySearchForm"})
public class GroceryStoreSearchController {

    private GroceryService groceryService;

    @GetMapping("/grocerySearch")
    public String filterGroceries(Model model)
    {
        model.addAttribute("groceryCategoryList", GroceryCategory.values());
        if (!model.containsAttribute("grocerySearchForm")) {
            model.addAttribute("grocerySearchForm", new GrocerySearchForm());
        }
        if (!model.containsAttribute("grocery"))
        {
            model.addAttribute( "grocery", new ArrayList<>());
        }
        return "store/groceryFilter";
    }

    @PostMapping("/grocery")
    public String showFilteredGroceries(Model model, GrocerySearchForm grocerySearchForm)
    {
        model.addAttribute( "groceries",groceryService.filterByCriteria(grocerySearchForm));
        model.addAttribute( "grocerySearchForm", grocerySearchForm);
        return "redirect:store/groceryStore";
    }
}
