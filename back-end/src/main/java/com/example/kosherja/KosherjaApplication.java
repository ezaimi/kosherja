package com.example.kosherja;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages ={ "com.example.kosherja.Controller","com.example.kosherja.Model","com.example.kosherja.Repo","com.example.kosherja.Service"})

public class KosherjaApplication {

	public static void main(String[] args) {
		SpringApplication.run(KosherjaApplication.class, args);
	}

}
