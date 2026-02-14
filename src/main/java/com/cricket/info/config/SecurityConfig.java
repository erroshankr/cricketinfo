package com.cricket.info.config;

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
                       .requestMatchers("/login", "/register","/css/**","/js/**").permitAll()
                       .requestMatchers("/team/**").hasAnyRole("TEAM-ADMIN","SUPER-ADMIN")
                       .requestMatchers("/player/**").hasAnyRole("PLAYER-ADMIN","SUPER-ADMIN")
                       .requestMatchers("/match/**").hasAnyRole("MATCH-ADMIN","SUPER-ADMIN")
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
               );

       return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
