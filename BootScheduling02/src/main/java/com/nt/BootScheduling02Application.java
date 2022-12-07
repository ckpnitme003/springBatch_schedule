package com.nt;

import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BootScheduling02Application {

	public static void main(String[] args) {
		SpringApplication.run(BootScheduling02Application.class, args);
		System.out.println("Main class"+new Date()+" ,Thread name :"+Thread.currentThread().getName());
	}

}
