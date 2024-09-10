package hr.grocery.store.grocerystore.filter;


import hr.grocery.store.grocerystore.service.LogServiceImpl;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class AddressFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(AddressFilter.class);

    private LogServiceImpl logService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        if ("POST".equalsIgnoreCase(httpRequest.getMethod()) && "/login".equals(httpRequest.getServletPath())) {
            String address = httpRequest.getHeader("X-Forwarded-For");
            if (address == null || address.isEmpty()) {
                address = request.getRemoteAddr();
            }

            logger.info("There was a login attempt from this address: " + address);
        }

        chain.doFilter(request, response);
    }
}
