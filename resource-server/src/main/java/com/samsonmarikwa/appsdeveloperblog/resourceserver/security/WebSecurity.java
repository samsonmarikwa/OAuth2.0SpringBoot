package com.samsonmarikwa.appsdeveloperblog.resourceserver.security;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

// secureEnabled allows the @Secure annotation to be used at method or class level in controllers
// prePostEnabled allows two annotations to be enabled, preAuthorize and postAuthorize at method level class in controllers
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@EnableWebSecurity   // we can configure security on a very granular level. We can configure security on all or specific endpoints
public class WebSecurity extends WebSecurityConfigurerAdapter {
   
   @Override
   protected void configure(HttpSecurity http) throws Exception {
   
      JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
      jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KeycloakRoleConverter());
      
      // cors should not be configured on both API gateway and resource server (surprising it was working)
      http  //.cors().and()
            .authorizeRequests((requests) -> {
               ((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)
                     requests
                           .antMatchers(HttpMethod.GET, "/users/status/check")
      //                     .hasAuthority("SCOPE_profile")
      //                     .antMatchers(HttpMethod.GET, "/users")
      //                     .hasAuthority("SCOPE_users_list")
                           .hasRole("developer")
      //                     .hasAnyAuthority("ROLE_developer") // if hasRole is used as shown above, do not put prefix with ROLE_
      //                     .hasAnyRole("developer", "user", "admin")
                           .anyRequest()).authenticated();
            });
      http.oauth2ResourceServer()
            .jwt()
            .jwtAuthenticationConverter(jwtAuthenticationConverter);
   }
   
   // Bean to configure CORS
/*   @Bean
   CorsConfigurationSource corsConfigurationSource() {
      CorsConfiguration corsConfiguration = new CorsConfiguration();
      corsConfiguration.setAllowedOrigins(Arrays.asList("*")); // we can have a list or simply a * to include all sources
      corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST"));   // or we can just put * to allow all methods
      corsConfiguration.setAllowedHeaders(Arrays.asList("*"));
   
      UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
      source.registerCorsConfiguration("/**", corsConfiguration);
      
      return source;
   }*/
}
