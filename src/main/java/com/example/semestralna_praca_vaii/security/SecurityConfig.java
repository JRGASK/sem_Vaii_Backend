package com.example.semestralna_praca_vaii.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(proxyTargetClass = true,
                        securedEnabled = true,
                        jsr250Enabled = true)
public class SecurityConfig {


   @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

       http.csrf(AbstractHttpConfigurer::disable)
               .cors(Customizer.withDefaults())
               .authorizeHttpRequests((authz) -> authz
                       .requestMatchers(new AntPathRequestMatcher("/v3/api-docs/**"))
                       .permitAll()
                       .requestMatchers(new AntPathRequestMatcher("/swagger-ui/**"))
                       .permitAll()
                       .requestMatchers(new AntPathRequestMatcher("/**"))
                       .authenticated()
               ).sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
               .httpBasic(Customizer.withDefaults());

       return http.build();
    }
}
