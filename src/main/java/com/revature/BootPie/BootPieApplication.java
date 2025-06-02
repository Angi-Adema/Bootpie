package com.revature.BootPie;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.BootPie.models.Pie;
import com.revature.BootPie.services.PieService;

@SpringBootApplication
public class BootPieApplication {

	// See wich application file is being used.
	@Value("${spring.application.name}")
	private String appName;

	public static void main(String[] args) {
		SpringApplication.run(BootPieApplication.class, args);
	}


	@Bean
	public CommandLineRunner inspectorBean(ApplicationContext applicationContext) {
		return args -> {
			System.out.printf("Inspecting the beans provided by Spring Boot in %s....", appName).println();

			// Observe all the beans.
			String[] beans = applicationContext.getBeanDefinitionNames();
			Arrays.sort(beans);
			for (String bean : beans) {
				System.out.println(bean);
			}
			System.out.println("Ending our inspection...");
		};
	}
}
