package hr.grocery.store.grocerystore.config;

import hr.grocery.store.grocerystore.filter.AddressFilter;
import hr.grocery.store.grocerystore.handler.GroceryStoreAuthenticationSuccessHandler;
import hr.grocery.store.grocerystore.model.ShoppingCart;
import hr.grocery.store.grocerystore.service.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.thymeleaf.spring6.expression.Mvc;

import java.util.List;

@Configuration
@EnableWebSecurity
public class MvcSecurity {

    private final UserDetailsServiceImpl userDetailsService;

    public MvcSecurity(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    SecurityFilterChain web(HttpSecurity http, GroceryStoreAuthenticationSuccessHandler handler) throws Exception {
        http
                .addFilterBefore(new AddressFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/h2-console/**").permitAll()
                        .requestMatchers("/store/checkout","/store/myOrders", "/store/error", "/store/success").hasAuthority("USER")
//                        .requestMatchers("/store/categorySearch", "/store/grocerySearch", "/store/shoppingCart",
//                        "/store/").permitAll()
                        .requestMatchers("/store/**").permitAll()
                        .requestMatchers("/rest/**").permitAll()
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/admin/**").hasAuthority("ADMIN")
                        .anyRequest().authenticated()
                )
                .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**", "/login"))
                .formLogin(form -> form
//                                .defaultSuccessUrl("/store/grocerySearch")
                                .successHandler(handler)
                        )
                .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()))
                .logout(logout -> logout.logoutSuccessUrl("/").permitAll());

        return http.build();
    }

    @Bean
    @SessionScope
    public ShoppingCart cart() {
        return new ShoppingCart();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);
        configuration.setAllowedMethods(List.of("*"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowedOrigins(List.of("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public FilterRegistrationBean<AddressFilter> addressFilterRegistration() {
        FilterRegistrationBean<AddressFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new AddressFilter());
        registration.addUrlPatterns("/login/*");
        return registration;
    }

    @Bean
    public ServletListenerRegistrationBean<RequestContextListener> requestContextListener() {
        return new ServletListenerRegistrationBean<>(new RequestContextListener());
    }
}
