package com.example.kosherja;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages ={ "com.example.kosherja.Controller","com.example.kosherja.Model","com.example.kosherja.Repo","com.example.kosherja.Service"})

public class KosherjaApplication {

//	@Bean
//	public Jackson2ObjectMapperBuilderCustomizer customizeObjectMapper() {
//		return builder -> {
//			builder.simpleDateFormat("yyyy-MM-dd");
//			builder.modules(new JavaTimeModule());
//		};
//	}

	public static void main(String[] args) {
		SpringApplication.run(KosherjaApplication.class, args);
	}

}
