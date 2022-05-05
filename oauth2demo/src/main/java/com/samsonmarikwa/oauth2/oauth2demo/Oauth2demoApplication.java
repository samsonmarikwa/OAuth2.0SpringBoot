package com.samsonmarikwa.oauth2.oauth2demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import static javax.xml.crypto.dsig.DigestMethod.SHA256;

@SpringBootApplication
@Slf4j
public class Oauth2demoApplication {

	public static void main(String[] args) {
		SpringApplication.run(Oauth2demoApplication.class, args);
	}
	
	@Bean
	CommandLineRunner runner() {
		return args -> {
			String generateCodeVerifier = generateCodeVerifier();
		};
	}
	
	private String generateCodeVerifier() throws NoSuchAlgorithmException {
		SecureRandom secureRandom = new SecureRandom();
		byte[] codeVerifier = new byte[32];
		secureRandom.nextBytes(codeVerifier);
		String code = Base64.getUrlEncoder().withoutPadding().encodeToString(codeVerifier);
		log.info("Code Verifier: {}", code);
		String codeChallenger = generateCodeChallenge(code);
		return code;
	}
	
	private String generateCodeChallenge(String codeVerifier) throws NoSuchAlgorithmException {
		byte[] bytes = codeVerifier.getBytes(StandardCharsets.US_ASCII);
		MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
		messageDigest.update(bytes, 0, bytes.length);
		byte[] digest = messageDigest.digest();
		String codeChallenger = Base64.getUrlEncoder().withoutPadding().encodeToString(digest);
		log.info("Code Challenger: {}", codeChallenger);
		return codeChallenger;
	}
}
