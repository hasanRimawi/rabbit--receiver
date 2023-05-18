package com.example.demo3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@ComponentScan({ "com.example.security", "com.example.controllers", "com.example.services", "com.example.configs" })
//@EnableDiscoveryClient
public class Demo3Application {

	public static void main(String[] args) {
		SpringApplication.run(Demo3Application.class, args);
		BCryptPasswordEncoder pass = new BCryptPasswordEncoder();
		System.out.println("************ down here ************");
		System.out.println(pass.encode("hi"));
		
	}

}
