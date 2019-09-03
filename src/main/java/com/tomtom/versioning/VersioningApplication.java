package com.tomtom.versioning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication
@EnableCaching
public class VersioningApplication {

	public static void main(String[] args) {
		SpringApplication.run(VersioningApplication.class, args);
	}

}
