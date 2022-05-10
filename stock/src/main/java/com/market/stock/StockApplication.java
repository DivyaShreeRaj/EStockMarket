package com.market.stock;

import org.axonframework.config.EventProcessingConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.market.stock.exception.StockServiceEventsErrorHandler;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

/**
 * StockApplication
 * 
 * @author User
 *
 */
@SpringBootApplication
public class StockApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockApplication.class, args);
	}

	@Autowired
	public void configure(EventProcessingConfigurer configurer) {
		configurer.registerListenerInvocationErrorHandler("stock",
				configuration -> new StockServiceEventsErrorHandler());
	}

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI().info(new Info().title("Stock Management Service")
				.description("This microservice handles operations related to Stocks such as Stock addition, "
						+ "Retrieve stock details by company code within provided duration. "));
	}

}
