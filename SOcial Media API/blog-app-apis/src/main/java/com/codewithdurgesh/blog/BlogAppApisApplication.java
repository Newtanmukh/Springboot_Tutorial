package com.codewithdurgesh.blog;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@SpringBootApplication// this means that it is the main application and the main class here. this will provide configuration and notation figure.
public class BlogAppApisApplication {


	public static void main(String[] args) {

		SpringApplication.run(BlogAppApisApplication.class, args);
	}


	//we need to declare a bean called modelmapper here. we can then autowire it and use it for model mapping(entity conversion)
	//or we can make our own confighuration class and then declare bean inside it.
	@Bean //we need to declare it bean to make sure that like, spring creates an object for us whenever we neeed it and we can use autowiring there.
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}





}
