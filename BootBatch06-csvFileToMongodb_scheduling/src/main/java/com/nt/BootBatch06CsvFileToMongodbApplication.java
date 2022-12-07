package com.nt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BootBatch06CsvFileToMongodbApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootBatch06CsvFileToMongodbApplication.class, args);
	}

}
