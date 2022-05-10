package com.market.company;

import org.axonframework.config.EventProcessingConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.market.company.exception.CompanyServiceEventsErrorHandler;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

/**
 * CompanyApplication
 * 
 * @author User
 *
 */
@SpringBootApplication
@EnableMongoRepositories
public class CompanyApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompanyApplication.class, args);
	}

	@Autowired
	public void configure(EventProcessingConfigurer configurer) {
		configurer.registerListenerInvocationErrorHandler("company",
				configuration -> new CompanyServiceEventsErrorHandler());
	}

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI().info(new Info().title("Company Management Service").description(
				"This microservice handles operations related to company such as Company registration, Company deletion, "
						+ "Retrieve company details by company code, Retrieve all company details. "));
	}

}
