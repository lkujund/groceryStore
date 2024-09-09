package hr.grocery.store.grocerystore.controller;

import hr.grocery.store.grocerystore.dto.GroceryCategoryDTO;
import hr.grocery.store.grocerystore.dto.GroceryDTO;
import hr.grocery.store.grocerystore.model.Grocery;
import hr.grocery.store.grocerystore.model.GroceryCategoryEnum;
import hr.grocery.store.grocerystore.model.MeasuringUnitEnum;
import hr.grocery.store.grocerystore.service.GroceryCategoryService;
import hr.grocery.store.grocerystore.service.GroceryService;
import hr.grocery.store.grocerystore.service.LogService;
import hr.grocery.store.grocerystore.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Controller
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {
    private GroceryService groceryService;
    private GroceryCategoryService groceryCategoryService;
    private LogService logService;
    private OrderService orderService;


    //GROCERY

    @GetMapping("/manageGroceries")
    public String showManageGroceries(Model model) {
        List<Grocery> groceryList = groceryService.findAllAdmin();
        groceryList.forEach(grocery -> {
            if (grocery.getImage() != null) {
                grocery.setB64Image(Base64.getEncoder().encodeToString(grocery.getImage()));
            }
        });
        model.addAttribute("grocery", groceryService.findAllAdmin());
        return "admin/manageGroceries";
    }

    @GetMapping("/addGrocery")
    public String showAddGroceryForm(Model model) {
        model.addAttribute("groceryCategoryList", groceryCategoryService.findAll());
        model.addAttribute("measuringUnitList", MeasuringUnitEnum.values());
        model.addAttribute("groceryDTO", new GroceryDTO());
        return "admin/addGrocery";
    }



    @PostMapping("/addGrocery")
    public String saveNewGrocery(@ModelAttribute GroceryDTO groceryDTO, Model model) throws IOException {
        groceryService.save(groceryDTO);
        return "redirect:manageGroceries";
    }

    @GetMapping("/editGrocery/{id}")
    public String showEditGroceryForm( @PathVariable int id, Model model) {
        model.addAttribute("groceryCategoryList", groceryCategoryService.findAll());
        model.addAttribute("measuringUnitList", MeasuringUnitEnum.values());
        model.addAttribute("groceryDTO", groceryService.findById(id).get());
        model.addAttribute("grocery", groceryService.findByIdAdmin(id).get());
        return "admin/editGrocery";
    }

    @PostMapping("/editGrocery/{id}")
    public String editGrocery(@PathVariable int id, @ModelAttribute GroceryDTO groceryDTO, Model model) throws IOException {
        groceryService.edit(groceryDTO, id);
        return "redirect:../manageGroceries";
    }

    @PostMapping("/deleteGrocery/{id}")
    public String deleteGrocery(@PathVariable int id) {
        groceryService.deleteById(id);
        return "redirect:../manageGroceries";
    }




    //GROCERY CATEGORY

    @GetMapping("/manageGroceryCategories")
    public String showManageGroceryCategories(Model model) {
        model.addAttribute("groceryCategoryList", groceryCategoryService.findAllAdmin());
        return "admin/manageGroceryCategories";
    }

    @GetMapping("/addGroceryCategory")
    public String showAddGroceryCategoryForm(Model model) {
        model.addAttribute("groceryCategoryDTO", new GroceryCategoryDTO());
        return "admin/addGroceryCategory";
    }

    @PostMapping("/addGroceryCategory")
    public String saveNewGroceryCategory(@ModelAttribute GroceryCategoryDTO groceryCategoryDTO, Model model) {
        groceryCategoryService.save(groceryCategoryDTO);
        return "redirect:manageGroceryCategories";
    }

    @GetMapping("/editGroceryCategory/{id}")
    public String showEditGroceryCategoryForm(Model model, @PathVariable int id) {
        model.addAttribute("groceryCategory", groceryCategoryService.findByIdAdmin(id).get());
        return "admin/editGroceryCategory";
    }

    @PostMapping("/editGroceryCategory/{id}")
    public String editGroceryCategory(@ModelAttribute GroceryCategoryDTO groceryCategoryDTO, Model model, @PathVariable int id) {
        groceryCategoryService.edit(groceryCategoryDTO, id);
        return "redirect:../manageGroceryCategories";
    }

    @PostMapping("/deleteGroceryCategory/{id}")
    public String deleteGroceryCategory(@PathVariable int id) {
        groceryCategoryService.deleteById(id);
        return "redirect:../manageGroceryCategories";
    }



    //ACTIVITY LOG, ALL ORDERS

    @GetMapping("/viewAllOrders")
    public String showViewAllOrders(Model model) {
        model.addAttribute("orders", orderService.getOrders());
        return "admin/viewAllOrders";
    }

    @GetMapping("/viewActivityLog")
    public String showViewActivityLog(Model model) {
        model.addAttribute("logs", logService.getLogs());
        return "admin/viewActivityLog";
    }
}
