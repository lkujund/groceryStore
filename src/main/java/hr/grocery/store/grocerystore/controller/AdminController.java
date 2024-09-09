package hr.grocery.store.grocerystore.controller;

import hr.grocery.store.grocerystore.dto.GroceryDTO;
import hr.grocery.store.grocerystore.model.GroceryCategoryEnum;
import hr.grocery.store.grocerystore.model.MeasuringUnitEnum;
import hr.grocery.store.grocerystore.service.GroceryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {
    private GroceryService groceryService;

    @GetMapping("/addGrocery")
    public String showAddGroceryForm(Model model) {
        model.addAttribute("groceryCategoryList", GroceryCategoryEnum.values());
        model.addAttribute("measuringUnitList", MeasuringUnitEnum.values());
        model.addAttribute("groceryDTO", new GroceryDTO());
        return "admin/addGrocery";
    }



    @PostMapping("/addGrocery")
    public String saveNewGrocery(@ModelAttribute GroceryDTO groceryDTO, Model model) {
        groceryService.save(groceryDTO);
        return "redirect:addGrocery";
    }
}
