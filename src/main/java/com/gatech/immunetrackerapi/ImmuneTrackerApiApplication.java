package com.gatech.immunetrackerapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class ImmuneTrackerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImmuneTrackerApiApplication.class, args);
	}

}
