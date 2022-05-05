package com.samsonmarikwa.photoappwebclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class PhotoappwebclientApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhotoappwebclientApplication.class, args);
	}
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	// The WebClient bean is not configured to work with oauth2, so we need to configure a filter. We inject the
	// two repositories that allow us to configure the filter and apply its configuration to the Webclient object.
	// The filter will include the client access token into every http request that is sent.
	public WebClient webClient(ClientRegistrationRepository clientRegistrationRepo,
										OAuth2AuthorizedClientRepository authClientRepo) {
		//	This configuration makes an access token be included into every single http request that is sent using this
		// webclient object, but be careful not to use this webclient object to communicate with other third-party
		// webservices because otherwise you would sent out the user's access token to a third-party webservice and an
		// access token would become compromised. If you need an access token sent to a third-party webservice, use a
		// different object of http client.
		ServletOAuth2AuthorizedClientExchangeFilterFunction oAuth2 =
				new ServletOAuth2AuthorizedClientExchangeFilterFunction(clientRegistrationRepo, authClientRepo);
		oAuth2.setDefaultOAuth2AuthorizedClient(true);
		return WebClient.builder().apply(oAuth2.oauth2Configuration()).build();
	}

}
