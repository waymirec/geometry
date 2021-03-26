package io.nuvalence.geometry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class GeometryApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeometryApplication.class, args);
	}

}
