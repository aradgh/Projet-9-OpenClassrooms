package com.openclassrooms.patientservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable) // API stateless
            .headers(headers -> headers.frameOptions().sameOrigin() // Permet l'affichage en iframe pour H2
            ).authorizeHttpRequests(auth -> auth.requestMatchers("/actuator/health").permitAll()  // optionnel
                .requestMatchers("/h2-console/**").permitAll() // Autoriser la console H2
                .anyRequest().authenticated()).httpBasic(Customizer.withDefaults()); // HTTP Basic
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    UserDetailsService users(PasswordEncoder encoder) {
        // utilisateurs en mémoire
        UserDetails user = User.withUsername("user").password(encoder.encode("userpass")).roles("USER").build();

        UserDetails admin = User.withUsername("admin").password(encoder.encode("adminpass")).roles("ADMIN").build();

        return new InMemoryUserDetailsManager(user, admin);
    }
}