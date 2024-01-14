package com.springboot.blog.springbootblogrestapi;


import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;



@SpringBootApplication
@OpenAPIDefinition(
	info = @Info(
		title = "Spring Boot Social Media App REST APIs",
		description = "Spring Boot Blog App REST APIs Documentation",
		version = "v1.0",
		contact = @Contact(
			name = "Chris",
			email = "chrischenlixing@gmail.com",
			url = "https://github.com/chrischenlixing"
		),
		license = @License(
			name = "Apache 2.0",
			url = "https://www.apache.org/licenses/LICENSE-2.0"
		)
	),
		externalDocs = @ExternalDocumentation(
			description = "Spring Boot Social Media App Documentation",
			url =  "https://github.com/chrischenlixing/SocialMediaApi"
		)
		
)
public class SpringbootBlogRestApiApplication {
	@Bean
	public ModelMapper modelMapper(){
		ModelMapper modelMapper = new ModelMapper();
		    modelMapper.getConfiguration()
        .setMatchingStrategy(MatchingStrategies.STRICT);
		return modelMapper;
	}
	public static void main(String[] args) {
		SpringApplication.run(SpringbootBlogRestApiApplication.class, args);
	}

}
