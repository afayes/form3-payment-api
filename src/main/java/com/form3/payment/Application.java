package com.form3.payment;

import static springfox.documentation.builders.PathSelectors.ant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * The entry point for the application.
 */
@SpringBootApplication
@EnableSwagger2
public class Application {

	public static final String VERSION = "/api/v1" ;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public static Docket submissionApi() {
		ApiInfo apiInfo = new ApiInfoBuilder()
				.title("Payment API")
				.description("Payment API")
				.build();

		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.form3.payment"))
				.paths(ant("/**/*"))
				.build();
	}
}
