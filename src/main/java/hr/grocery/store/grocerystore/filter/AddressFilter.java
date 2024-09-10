package hr.grocery.store.grocerystore.filter;


import hr.grocery.store.grocerystore.model.Log;
import hr.grocery.store.grocerystore.model.UserEvent;
import hr.grocery.store.grocerystore.service.LogService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
public class AddressFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(AddressFilter.class);

    private LogService logService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;

        if (httpRequest.getMethod().equalsIgnoreCase("POST") && httpRequest.getServletPath().equals("/login")) {
            String address = httpRequest.getHeader("X-Forwarded-For");
            if (address== null || address.isEmpty()) {
                address = servletRequest.getRemoteAddr();
            }

//            Log log = new Log();
//
//            //TODO: zamijeniti s pravim userom
//            log.setName("user");
//            log.setLoginTs(Instant.now());
//            log.setAddress(address);
//            log.setUserEvent(UserEvent.USER_LOGGED_IN);

            logger.info("An attempt to log in has been made from this address: " + address);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
