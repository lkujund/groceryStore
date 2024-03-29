package hr.grocery.store.grocerystore.controller;

import hr.grocery.store.grocerystore.dto.GroceryDTO;
import hr.grocery.store.grocerystore.model.GroceryCategory;
import hr.grocery.store.grocerystore.model.MeasuringUnit;
import hr.grocery.store.grocerystore.service.GroceryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/store")
@AllArgsConstructor
public class GroceryStoreController {


    private GroceryService groceryService;

    @GetMapping("/addGrocery")
    public String showAddGroceryForm(Model model) {
        model.addAttribute("groceryCategoryList", GroceryCategory.values());
        model.addAttribute("measuringUnitList", MeasuringUnit.values());
        model.addAttribute("groceryDTO", new GroceryDTO());
        return "store/addGrocery";
    }

    @PostMapping("/addGrocery")
    public String saveNewGrocery(@ModelAttribute GroceryDTO groceryDTO, Model model) {
        groceryService.save(groceryDTO);
        return "redirect:addGrocery";
    }
}
