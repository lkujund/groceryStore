package hr.grocery.store.grocerystore.config;

import hr.grocery.store.grocerystore.filter.AddressFilter;
import hr.grocery.store.grocerystore.handler.GroceryStoreAuthenticationSuccessHandler;
import hr.grocery.store.grocerystore.model.ShoppingCart;
import hr.grocery.store.grocerystore.service.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
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

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class MvcSecurity {

    private final GroceryStoreAuthenticationSuccessHandler handler;
    private final UserDetailsServiceImpl userDetailsService;

    @Bean
    SecurityFilterChain web(HttpSecurity http) throws Exception {
        http
                .addFilterBefore(new AddressFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/h2-console/**").permitAll()
                        .requestMatchers("/store/categorySearch", "/store/grocerySearch", "/store/shoppingCart").permitAll()
                        .requestMatchers("/rest/**").permitAll()
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/store/checkout","/store/myOrders", "/store/error", "/store/success").hasRole("USER")
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**", "/login"))
                .formLogin(form -> form
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
    public UserDetailsService userDetailsService() {
        return userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
