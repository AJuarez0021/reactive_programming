package com.atomic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;

/**
 * The Class SpringWebfluxAcademyFunctionalApplication.
 */
@SpringBootApplication
@EnableR2dbcAuditing
public class SpringWebfluxAcademyFunctionalApplication {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(SpringWebfluxAcademyFunctionalApplication.class, args);
	}

}
