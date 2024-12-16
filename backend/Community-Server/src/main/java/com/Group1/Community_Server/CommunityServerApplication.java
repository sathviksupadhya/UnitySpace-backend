package com.Group1.Community_Server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class CommunityServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommunityServerApplication.class, args);
	}

}
