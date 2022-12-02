package dmacc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import dmacc.beans.Customer;
import dmacc.beans.Vehicle;
import dmacc.repository.CustomerRepository;
import dmacc.repository.VehicleRepository;

/**
 * @author Joe Shilkaitis - jshilkaitis
 * CIS175 - Fall 2022
 * Nov 12, 2022
 */
@Controller
public class VehicleController {
	@Autowired
	VehicleRepository vehicleRepo;
	
	@Autowired
	CustomerRepository customerRepo;

	
	@GetMapping("/viewAllVehicles")
	public String viewAllVehicles(Model model) {
		model.addAttribute("vehicle", vehicleRepo.findAll());
		return "viewAllVehicles";
		}
	
	@GetMapping("/addVehicle/{custId}")
	public String addVehicle(@PathVariable("custId") long custId,Model model) {
		Customer c = customerRepo.findById(custId).orElse(null);
		Vehicle v = new Vehicle();
		model.addAttribute("customer", c);
		model.addAttribute("newVehicle", v);
		return "addVehicle";
	}
	
	@PostMapping("/addVehicle/{custId}")
	public String addVehicle(@PathVariable("custId") long custId, @ModelAttribute Vehicle v, Customer c, Model model) {
		v.setCustomer(customerRepo.findById(custId).orElse(null));
		vehicleRepo.save(v);
		
		c = customerRepo.findById(custId).orElse(null);
		List<Vehicle> vehicle = new ArrayList<Vehicle>();
		vehicle.add(v);
		c.setVehicles(vehicle);
		customerRepo.save(c);
		
		return "viewAllCustomers";

	}
	
	@GetMapping("/editVehicle/{id}")
	public String editVehicle(@PathVariable("id") long id, Model model) {
		Vehicle v = vehicleRepo.findById(id).orElse(null);
		model.addAttribute("newVehicle", v);
		return "editVehicle";
	}

	@PostMapping("/updateVehicle/{id}")
	public String updateVehicle(Vehicle v, Model model) {
		vehicleRepo.save(v);
		return viewAllVehicles(model);
	}

	@GetMapping("/deleteVehicle/{id}")
	public String deleteVehicle(@PathVariable("id") long id, Model model) {
		Vehicle v = vehicleRepo.findById(id).orElse(null);
		vehicleRepo.delete(v);
		return viewAllVehicles(model);
	}
}
