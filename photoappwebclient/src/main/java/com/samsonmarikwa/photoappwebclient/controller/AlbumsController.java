package com.samsonmarikwa.photoappwebclient.controller;

import com.samsonmarikwa.photoappwebclient.response.AlbumRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

// A web client application and not a restful web service, hence we assign a @Controller notation
@Controller
public class AlbumsController {
   
   @Autowired
   OAuth2AuthorizedClientService oAuth2ClientService;
   
//   @Autowired
//   RestTemplate restTemplate;
   
   @Autowired
   WebClient webClient;
   
   @GetMapping("/albums")
   public String getAlbums(Model model,
                           @AuthenticationPrincipal OidcUser principal,
                           Authentication authentication) {
      
      // The authentication value injected can also be retrived from the SecurityContextHolder as shown below.
      // Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      
      /*
      * This RestTemplate code and the access token code is not required when using Webclient. So, I am block commenting
      * it out.
   
      OAuth2AuthenticationToken oAuthToken = (OAuth2AuthenticationToken) authentication;
   
      OAuth2AuthorizedClient oAuth2AuthorizedClient =
            oAuth2ClientService.loadAuthorizedClient(oAuthToken.getAuthorizedClientRegistrationId(), oAuthToken.getName());
      String jwtAccessToken = oAuth2AuthorizedClient.getAccessToken().getTokenValue(); // base64 JWT, verify on https://jwt.io
      System.out.println("jwtAccessToken: " + jwtAccessToken);
   
      System.out.println("Principal = " + principal);
      OidcIdToken idToken = principal.getIdToken();
      String tokenValue = idToken.getTokenValue();
      System.out.println("idTokenValue: " + tokenValue);
      */
      // Instead of the hard-coded albums below, we want to request from a protected resource, so we need to do that using
      // a restTemplate and the JWT. We will comment the below and add new code.
      /*
      AlbumRest album = new AlbumRest();
      album.setAlbumId("albumOne");
      album.setAlbumTitle("Album one title");
      album.setAlbumUrl("http://localhost:8082/albums/1");
   
      AlbumRest album2 = new AlbumRest();
      album2.setAlbumId("albumTwo");
      album2.setAlbumTitle("Album two title");
      album2.setAlbumUrl("http://localhost:8082/albums/2");
      
      model.addAttribute("albums", Arrays.asList(album, album2));
      */
      
      String url = "http://localhost:8084/albums";
      /*
      * Code not required for Webclient
      HttpHeaders headers = new HttpHeaders();
      headers.add("Authorization", "Bearer " + jwtAccessToken);
   
      HttpEntity<List<AlbumRest>> entity = new HttpEntity<>(headers);
   
      ResponseEntity<List<AlbumRest>> responseEntity =
            restTemplate.exchange(url, HttpMethod.GET, entity, new ParameterizedTypeReference<List<AlbumRest>>() {});
      
      List<AlbumRest> albums = responseEntity.getBody();
      */
   
      List<AlbumRest> albums = webClient
            .get()
            .uri(url)
            .retrieve()
            .bodyToMono(new ParameterizedTypeReference<List<AlbumRest>>(){})
            .block();
            model.addAttribute("albums", albums);
      return "albums";  // name of the view or html page
   }
}
