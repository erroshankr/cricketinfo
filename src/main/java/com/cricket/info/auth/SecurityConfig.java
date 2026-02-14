package com.cricket.info.auth;

import com.cricket.info.enums.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig { // this is the core of integration

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http){
       http
               .authorizeHttpRequests(auth -> auth
                       .requestMatchers("/login", "/register","/user-create","/css/**","/js/**").permitAll()

                       // Only write operations require admin roles (new, save, edit, delete)
                       .requestMatchers("/team/**")
                           .hasAnyRole(Role.TEAM_ADMIN.getRoleName(), Role.SUPER_ADMIN.getRoleName())
                       .requestMatchers("/player/**")
                           .hasAnyRole(Role.PLAYER_ADMIN.getRoleName(), Role.SUPER_ADMIN.getRoleName())
                       .requestMatchers("/match/**")
                           .hasAnyRole(Role.MATCH_ADMIN.getRoleName(), Role.SUPER_ADMIN.getRoleName())

                       // List, view, find pages -- any authenticated user
                       .anyRequest().authenticated()
               )
               .formLogin(form -> form
                       .loginPage("/login")
                       .loginProcessingUrl("/login")
                       .defaultSuccessUrl("/home", true)
                       .failureUrl("/login?error=true")
                       .permitAll()
               )
               .logout(logout -> logout
                       .logoutUrl("/logout")
                       .logoutSuccessUrl("/login?logout=true")
                       .invalidateHttpSession(true)
                       .deleteCookies("JSESSIONID")
                       .permitAll()
               )
               .exceptionHandling(ex -> ex
                       .accessDeniedPage("/access-denied")
               );

       return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
