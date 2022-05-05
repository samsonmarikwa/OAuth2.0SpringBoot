package com.samsonmarikwa.albums.ResourceServerAlbums;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ResourceServerAlbumsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResourceServerAlbumsApplication.class, args);
	}

}
