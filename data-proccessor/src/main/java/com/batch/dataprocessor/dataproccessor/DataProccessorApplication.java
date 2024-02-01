package com.batch.dataprocessor.dataproccessor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.batch.dataprocessor.dataproccessor.entity")
public class DataProccessorApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataProccessorApplication.class, args);
	}

}
