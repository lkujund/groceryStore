package hr.grocery.store.grocerystore.controller;

import hr.grocery.store.grocerystore.model.Grocery;
import hr.grocery.store.grocerystore.model.GroceryCategory;
import hr.grocery.store.grocerystore.model.GroceryCategoryEnum;
import hr.grocery.store.grocerystore.model.GrocerySearchForm;
import hr.grocery.store.grocerystore.service.GroceryCategoryService;
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
@SessionAttributes({"grocery", "grocerySearchForm", "category"})
public class GroceryStoreSearchController {

    private GroceryService groceryService;
    private GroceryCategoryService groceryCategoryService;

    @GetMapping("/grocerySearch")
    public String filterGroceries(Model model)
    {
        model.addAttribute("groceryCategoryList", GroceryCategoryEnum.values());
        if (!model.containsAttribute("grocerySearchForm")) {
            model.addAttribute("grocerySearchForm", new GrocerySearchForm());
        }
        if (!model.containsAttribute("grocery"))
        {
            model.addAttribute( "grocery", groceryService.findAllAdmin());
        }
        return "store/grocerySearch";
    }

    @PostMapping("/grocerySearch")
    public String showFilteredGroceries(Model model, GrocerySearchForm grocerySearchForm)
    {
        model.addAttribute( "grocery",groceryService.filterByCriteria(grocerySearchForm));
        model.addAttribute( "grocerySearchForm", grocerySearchForm);
        return "redirect:/store/grocerySearch";
    }

    @GetMapping("/categorySearch")
    public String filterCategories(Model model)
    {
        if (!model.containsAttribute("category"))
        {
            model.addAttribute( "category", groceryService.findAll());
        }
        return "store/categorySearch";
    }
}
