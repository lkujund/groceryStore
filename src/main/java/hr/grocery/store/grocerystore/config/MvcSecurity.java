package hr.grocery.store.grocerystore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class MvcSecurity {


    @Bean
    SecurityFilterChain web(HttpSecurity http) throws Exception {
        http
//                .addFilterBefore(new IpLog(), UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/h2-console/**").permitAll()
                        .requestMatchers("/store/**").permitAll()
                        .requestMatchers("/admin/**").permitAll()
                        .requestMatchers("/**").permitAll()
//                        .requestMatchers("/admin/**").hasRole("ADMIN")

                        .anyRequest().authenticated()
                )
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(form -> form.defaultSuccessUrl("/store/grocerySearch", true))
                .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()))

                .logout(logout -> logout
                        .logoutSuccessUrl("/").permitAll()
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public UserDetailsService users() {
//        UserDetails user = User.builder()
//                .username("user")
//                .password("{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW")
//                .roles("USER")
//                .build();
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password("{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW")
//                .roles("USER", "ADMIN")
//                .build();
//        UserDetails readOnly = User.builder()
//                .username("read_only")
//                .password("{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW")
//                .roles("READ_ONLY")
//                .build();
//        return new InMemoryUserDetailsManager(user, admin, readOnly);
//    }




}
