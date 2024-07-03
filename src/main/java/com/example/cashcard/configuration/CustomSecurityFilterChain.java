package com.example.cashcard.configuration;

import com.example.cashcard.exception.ProblemDetailsAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class CustomSecurityFilterChain {

    @Bean
    SecurityFilterChain appSecurity(HttpSecurity http,
                                    ProblemDetailsAuthenticationEntryPoint entryPoint) throws Exception {
        http
            .authorizeHttpRequests(authorize ->
                authorize
                    .requestMatchers(HttpMethod.GET, "/cashcards/**").hasAuthority("SCOPE_cashcard:read")
                    .requestMatchers("/cashcards/**").hasAuthority("SCOPE_cashcard:write")
                    .anyRequest().authenticated()
            )
            .oauth2ResourceServer(oauth2 -> oauth2
                .authenticationEntryPoint(entryPoint)
                .jwt(Customizer.withDefaults())
            );
        return http.build();
    }
}
