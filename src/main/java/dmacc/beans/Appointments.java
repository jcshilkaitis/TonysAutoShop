package dmacc.beans;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import dmacc.beans.Customer;
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
public class Appointments {
	@Id
	@GeneratedValue
	private long id;
	@OneToMany(mappedBy = "customer", cascade = CascadeType.MERGE)
	Customer customer;
	Date appointment;
}
