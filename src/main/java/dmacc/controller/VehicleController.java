package dmacc.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
	
	public String viewCustomerInformation(@PathVariable("id") long id, Model model) {
		Customer c = customerRepo.findById(id).orElse(null);
		model.addAttribute("customer", c);
		return "viewCustomerInformation";
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
		
		long tempId = v.getCustomer().getId();
		return viewCustomerInformation(tempId,model);

	}
	
	@GetMapping("/editVehicle/{id}")
	public String editVehicle(@PathVariable("id") long id, Model model) {
		Vehicle v = vehicleRepo.findById(id).orElse(null);
		Customer c = v.getCustomer();
		model.addAttribute("customer", c);
		model.addAttribute("newVehicle", v);
		return "editVehicle";
	}

	@PostMapping("/updateVehicle/{id}/{custId}")
	public String updateVehicle(@PathVariable("id") long id, @PathVariable("custId") long custId, Vehicle v, Customer c, Model model) {
		c = customerRepo.findById(custId).orElse(null);
		v.setCustomer(c);
		vehicleRepo.save(v);
		customerRepo.save(c);
		return viewCustomerInformation(c.getId(),model);
	}
	
	@GetMapping("/deleteVehicle/{id}")
	public String deleteVehicle(@PathVariable("id") long id, Model model) {
		Vehicle v = vehicleRepo.findById(id).orElse(null);
		Customer c = v.getCustomer();
		vehicleRepo.delete(v);
		return viewCustomerInformation(c.getId(),model);
	}
}
