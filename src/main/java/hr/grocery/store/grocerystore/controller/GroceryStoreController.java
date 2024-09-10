package hr.grocery.store.grocerystore.controller;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import hr.grocery.store.grocerystore.event.AddToCartEvent;
import hr.grocery.store.grocerystore.model.CartGrocery;
import hr.grocery.store.grocerystore.model.Grocery;
import hr.grocery.store.grocerystore.service.GroceryCategoryService;
import hr.grocery.store.grocerystore.service.GroceryService;
import hr.grocery.store.grocerystore.service.PayPalService;
import hr.grocery.store.grocerystore.service.ShoppingCartService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/store")
@AllArgsConstructor
public class GroceryStoreController {
    private GroceryService groceryService;
    private ShoppingCartService shoppingCartService;
    private GroceryCategoryService groceryCategoryService;
    private ApplicationEventPublisher eventPublisher;
    private PayPalService payPalService;
    private HttpServletRequest request;


    //SHOPPING CART

    @PostMapping("/addToCart")
    public String addToCart(@RequestParam("id") int id,
                            @RequestParam("amount") int amount)
    {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String username = authentication.getName();

        Grocery grocery = groceryService.findByIdAdmin(id).get();

        CartGrocery cartGrocery = new CartGrocery();
        cartGrocery.setGrocery(grocery);
        cartGrocery.setAmount(amount);


        eventPublisher.publishEvent(new AddToCartEvent(this, cartGrocery));

        return "redirect:shoppingCart";
    }

    @PostMapping("/changeAmount")
    public String changeGroceryAmount(@RequestParam("id") int id,
                            @RequestParam("amount") int amount)
    {
        shoppingCartService.changeGroceryAmount(id, amount);

        return "redirect:shoppingCart";
    }

    @PostMapping("/removeGrocery")
    public String removeGrocery(@RequestParam("id") int id)
    {
        shoppingCartService.removeGrocery(id);

        return "redirect:shoppingCart";
    }


    @GetMapping("/shoppingCart")
    public String viewShoppingCart(Model model)
    {
        List<CartGrocery> cartGroceries = shoppingCartService.getCartGroceryList();
        model.addAttribute("cartGroceries", cartGroceries);
        model.addAttribute("total", cartGroceries.stream().mapToDouble(g -> g.getGrocery().getPrice().doubleValue() * g.getAmount()).sum());

        return "store/shoppingCart";
    }

    //CHECKOUT

    @GetMapping("/checkout")
    public String proceedToCheckout(Model model)
    {
        List<CartGrocery> cartGroceries = shoppingCartService.getCartGroceryList();
        model.addAttribute("cartGroceries", cartGroceries);
        model.addAttribute("total", cartGroceries.stream().mapToDouble(g -> g.getGrocery().getPrice().doubleValue() * g.getAmount()).sum());

        return "store/checkout";
    }


    //PAYMENT

    @GetMapping("/payPal")
    public RedirectView payWithPayPal(){

        Double totalPrice = shoppingCartService.getCartGroceryList()
                                                .stream()
                                                .mapToDouble(g -> g.getGrocery().getPrice().doubleValue() * g.getAmount())
                                                .sum();

        String description = "Purchase of groceries on Grocery Store website";

        try{
            String baseUrl = request.getRequestURL().toString();
            baseUrl = baseUrl.replace(request.getRequestURI(), request.getContextPath());

            String cancelUrl = baseUrl + "/store/cancel";
            String successUrl = baseUrl + "/store/success";
            Payment payment = payPalService.createPayment(
                    totalPrice,
                    "EUR",
                    description,
                    cancelUrl,
                    successUrl);

            Optional<Links> links = payment.getLinks().stream().filter(l -> l.getRel().equals("approval_url")).findFirst();
            if (links.isPresent()) {
                return new RedirectView(links.get().getHref());
            }
        } catch (PayPalRESTException e) {
            return new RedirectView("/store/error");
        }
        return new RedirectView("/store/error");
    }

    @GetMapping("/success")
    public String successfulPayPalPayment(
            @RequestParam("paymentId") String paymentId,
            @RequestParam("PayerID") String payerId
    ){
        try {
            shoppingCartService.pay("PAYPAL");
            Payment payment = payPalService.doPayment(paymentId, payerId);
            System.out.println(payment.toJSON());
            if(payment.getState().equals("approved")){
                return "store/success";
            }
        } catch (PayPalRESTException e) {
            System.err.println(e.getDetails());
            return "redirect:/payment/error";
        }
        return "redirect:/payment/error";
    }
}
