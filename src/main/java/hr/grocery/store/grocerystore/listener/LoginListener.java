package hr.grocery.store.grocerystore.listener;

import hr.grocery.store.grocerystore.model.UserEvent;
import hr.grocery.store.grocerystore.service.LogServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class LoginListener implements ApplicationListener<AuthenticationSuccessEvent> {

    private final LogServiceImpl logService;

    public LoginListener(LogServiceImpl logService) {
        this.logService = logService;
    }

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = attr.getRequest();

        String address = request.getRemoteAddr();
        UserDetails userDetails = (UserDetails) event.getAuthentication().getPrincipal();

        System.out.println("Login success for user: " + userDetails.getUsername() + " from IP: " + address);

        logService.logLogin(userDetails.getUsername(), address, UserEvent.USER_LOGGED_IN);
    }
}
