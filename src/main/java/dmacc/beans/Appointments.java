package dmacc.beans;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

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
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	private Customer customer;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date appointment;
	private String reason;
}
