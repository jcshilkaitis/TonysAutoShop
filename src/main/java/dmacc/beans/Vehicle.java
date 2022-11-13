package dmacc.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Joe Shilkaitis - jshilkaitis
 * CIS175 - Fall 2022
 * Nov 12, 2022
 */
@Entity
@Data
@NoArgsConstructor
public class Vehicle {
	@Id
	@GeneratedValue
	private int id;
	private String make;
	private String model;
	private int year;

	public Vehicle(String make, String model, int year) {
		super();
		this.make = make;
		this.model = model;
		this.year = year;
	}

	public Vehicle(int id, String make, String model, int year) {
		super();
		this.id = id;
		this.make = make;
		this.model = model;
		this.year = year;
	}

}
