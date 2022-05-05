package com.samsonmarikwa.clients.sociallogin.SocialLoginWebClient.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
   
   @Autowired
   ClientRegistrationRepository clientRegistrationRepo;
   
   @Override
   protected void configure(HttpSecurity http) throws Exception {
      http
            .authorizeRequests()
            .antMatchers("/").permitAll()
            .anyRequest().authenticated()
            .and()
            .oauth2Login()
            .and()
            .logout()
//            .logoutSuccessUrl("/")  // We do not need this if logoutSuccessHandler is specified
            .logoutSuccessHandler(oidcLogoutSuccessHandler())
            .invalidateHttpSession(true)  // default behaviour in Spring
            .clearAuthentication(true)    // default behaviour in Spring
            .deleteCookies("JSESSIONID"); // Cookies can be comma-separated
   }
   
   private LogoutSuccessHandler oidcLogoutSuccessHandler() {
      // SpringFramework does the heavy lifting in that it determines the endpoint to use to invalidate the session
      // You only need to determine if the ID provider has the end_session_endpoint as indicated in the properties file
      OidcClientInitiatedLogoutSuccessHandler successHandler =
            new OidcClientInitiatedLogoutSuccessHandler(clientRegistrationRepo);
      successHandler.setPostLogoutRedirectUri("http://localhost:8086/");
      return successHandler;
   }
}
