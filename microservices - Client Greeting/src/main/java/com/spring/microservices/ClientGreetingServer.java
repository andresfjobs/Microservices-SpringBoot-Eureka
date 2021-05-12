package com.spring.microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan(useDefaultFilters = false) // Disable component scanner
public class ClientGreetingServer {

	public static final String SERVICE_URL = "http://greeting-service";

	public static void main(String[] args) {
		SpringApplication.run(ClientGreetingServer.class, args);
	}

	//A customized RestTemplate that has the ribbon load balancer build in
	@LoadBalanced
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	// The service encapsulates the interaction with the micro-service.
	@Bean
	public ClientGreetingService helloWorldService() {
		return new ClientGreetingService(SERVICE_URL);
	}

	//Create the controller, passing it the ClientGreetingService to use.
	@Bean
	public ClientGreetingController helloWorldController() {
		return new ClientGreetingController(helloWorldService());
	}

	@Bean
	public ClientGreetingHomeController homeController() {
		return new ClientGreetingHomeController();
	}
}