package com.atomic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;

/**
 * The Class SpringWebfluxAcademyApplication.
 */
@SpringBootApplication
@EnableR2dbcAuditing
public class SpringWebfluxAcademyApplication {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(SpringWebfluxAcademyApplication.class, args);
	}

}
