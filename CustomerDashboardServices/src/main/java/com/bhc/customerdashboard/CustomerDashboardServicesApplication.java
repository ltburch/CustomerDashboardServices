package com.bhc.customerdashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
@ComponentScan("com.bhc")
public class CustomerDashboardServicesApplication {

	public static void main(String[] args) {
	    
		SpringApplication.run(CustomerDashboardServicesApplication.class, args);
	}
}
