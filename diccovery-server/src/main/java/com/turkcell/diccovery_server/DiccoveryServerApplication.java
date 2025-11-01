package com.turkcell.diccovery_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DiccoveryServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiccoveryServerApplication.class, args);
	}

}
