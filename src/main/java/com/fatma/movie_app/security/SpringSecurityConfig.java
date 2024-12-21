package com.fatma.movie_app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfig {
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authRequest->{
//            authRequest.anyRequest().permitAll();
            authRequest.requestMatchers("/auth/**").permitAll();
            authRequest .requestMatchers("/movies/add").hasRole("ADMIN");
            authRequest .requestMatchers("/movies/add/**").hasRole("ADMIN");

            authRequest .requestMatchers("/movies/removeMovie/**").hasRole("ADMIN");
            authRequest .requestMatchers("/movies/removeAllMovies").hasRole("ADMIN");
            authRequest .requestMatchers("/movies/fetchMovieFromOMDb").hasRole("ADMIN");
            authRequest .requestMatchers("/movies/fetchMovieListFromOMDb").hasRole("ADMIN");

            authRequest .requestMatchers("/movies/getMovie/**").hasAnyRole("USER","ADMIN");
            authRequest .requestMatchers("/movies/getAllMovies").hasAnyRole("USER","ADMIN");
            authRequest .requestMatchers("/movies/search").hasAnyRole("USER","ADMIN");
            authRequest .requestMatchers("/movies/rateMovie").hasRole("USER");

            authRequest .anyRequest().authenticated();

        });
        http.csrf(AbstractHttpConfigurer::disable);
        http.cors(AbstractHttpConfigurer::disable);
        http.headers(AbstractHttpConfigurer::disable);
        http.formLogin(Customizer.withDefaults());
        http.httpBasic(Customizer.withDefaults());
        return http.build();

    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

}
