package com.samsonmarikwa.appsdeveloperblog.resourceserver.controllers;

import com.samsonmarikwa.appsdeveloperblog.resourceserver.response.UserRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersController {
   
   @Autowired
   Environment env;
   
   @GetMapping("/status/check")
   public String status() {
      // We can log the port number so that we can know which port the microservice is using
      String portNumber = env.getProperty("local.server.port");
      return "Working on port: " + portNumber;
   }
   
   @GetMapping
   public String getUsers() {
      return "All users";
   }
   
   // @Secured("ROLE_developer")
   /* Decides whether a method can be invoked or not, it allows us to write security functions*/
   // @PreAuthorize("hasRole('developer')")
   
   /* will only execute if the authenticated user has a developer role assigned to it.*/
   // @PreAuthorize("hasAuthority('ROLE_developer')")
   /*
    * executes if authenticated user has a developer role or if the authenticated user's id matches
    * the path variable (in other words, if the authenticated user owns the record). The authenticated
    * user is available as a sub (subject) in the token, use jwt.io to find the value of sub
    */
   @PreAuthorize("hasAuthority('ROLE_developer') or #id == #jwt.subject")
   @DeleteMapping("/{id}")
   public String deleteUser(@PathVariable String id, @AuthenticationPrincipal Jwt jwt) {
      return "Deleted user with id " + id;
   }
   
   /* The PostAuthorize evaluates after the method has executed. This should not be used for mutating
    * methods, such as delete as the delete would take place before the PostAuthorize rule is evaluated.
    * executes if authenticated user has a developer role or if the authenticated user's id matches
    * the path variable (in other words, if the authenticated user owns the record). The authenticated
    * user is available as a sub (subject) in the token, use jwt.io to find the value of sub
    */
   @PostAuthorize("returnObject.userId == #jwt.subject")
   @GetMapping("/{id}")
   public UserRest getUser(@PathVariable String id, @AuthenticationPrincipal Jwt jwt) {
      return new UserRest("Samson", "Marikwa", "5cb63c5c-f0a2-48c3-b6f7-01c4c4796933");
   }
}
