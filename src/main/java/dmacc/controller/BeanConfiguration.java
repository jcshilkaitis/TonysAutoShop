package dmacc.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dmacc.beans.Vehicle;

/**
 * @author Joe Shilkaitis - jshilkaitis
 * CIS175 - Fall 2022
 * Nov 12, 2022
 */
@Configuration
public class BeanConfiguration {
	@Bean
	public Vehicle vehicle() {
		Vehicle bean = new Vehicle();
		return bean;
	}
}
