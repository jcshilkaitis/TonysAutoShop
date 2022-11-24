package dmacc.beans;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Zachary Lawless - zglawless
 * CIS175 - Fall 2022
 * Nov 15, 2022
 */
@Entity
@Data
@NoArgsConstructor
public class Customer {
	@Id
	@GeneratedValue
	private long id;
	private String firstName;
	private String lastName;
	private String email;
	@OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
	private List<Appointments> appointments;
	@OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
	private List<Vehicle> vehicles;
	
}
