package com.samsonmarikwa.OrdersResourceServer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class WebSecurityConfig {
   
   @Bean
   SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
      // Make communication stateless to ensure previous communication cookies are not remembered.
      // This ensures the JSESSION cookie is not remembered to avoid subsequent access to resources without JWT
      http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
      
      // Configure antMatchers if needed
      http.authorizeRequests()
            .antMatchers("/orders/status/check").permitAll()
            .anyRequest().authenticated()
            .and()
            .oauth2ResourceServer()
            .jwt();
      
      return http.build();
      
   }
}
