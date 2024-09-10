package hr.grocery.store.grocerystore.listener;

import hr.grocery.store.grocerystore.model.Log;
import hr.grocery.store.grocerystore.model.UserEvent;
import hr.grocery.store.grocerystore.repository.SpringDataJpaLogRepository;
import hr.grocery.store.grocerystore.service.LogService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import java.time.Instant;

@Component
@AllArgsConstructor
public class AuthenticationSuccessListener implements ApplicationListener<AuthenticationSuccessEvent> {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationSuccessListener.class);
    private SpringDataJpaLogRepository logRepository;
    private HttpServletRequest request;


    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        Authentication authentication = event.getAuthentication();
        if (authentication.getPrincipal() instanceof User) {
            User user = (User) authentication.getPrincipal();
            String username = user.getUsername();

            String address = request.getHeader("X-Forwarded-For");
            if (address == null || address.isEmpty()) {
                address = request.getRemoteAddr();
            }

            Log log = new Log();
            log.setName(username);
            log.setLoginTs(Instant.now());
            log.setAddress(address);
            log.setUserEvent(UserEvent.USER_LOGGED_IN);

            logRepository.save(log);

            logger.info("User {} logged in from IP address: {}", username, address);
        }
    }
}
