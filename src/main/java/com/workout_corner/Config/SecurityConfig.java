package com.workout_corner.Config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;
    // FOR TESTING
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception { //for testing
//        http
//                .csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(req ->
//                        req.anyRequest().permitAll()
//                )
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//        return http.build();
//    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req ->
                        req
                                .requestMatchers("/api/auth/**") .permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/categories/**").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/products/**").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/reviews/**").permitAll()
                                .requestMatchers("/api/users/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.POST, "/api/categories/**").hasAnyRole("ADMIN", "MODERATOR")
                                .requestMatchers(HttpMethod.POST, "/api/products/**").hasAnyRole("ADMIN", "MODERATOR")
                                .anyRequest()
                                .authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout ->
                        logout.logoutUrl("/api/auth/logout")
                                .addLogoutHandler(logoutHandler)
                                .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
                )
        ;
        return http.build();
    }
}