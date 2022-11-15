package dmacc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import dmacc.beans.Vehicle;
import dmacc.repository.VehicleRepository;

/**
 * @author Joe Shilkaitis - jshilkaitis
 * CIS175 - Fall 2022
 * Nov 12, 2022
 */
@Controller
public class VehicleController {
	@Autowired
	VehicleRepository repo;
	
	@GetMapping("/viewAllVehicles")
	public String viewAllVehicles(Model model) {
		model.addAttribute("vehicle", repo.findAll());
		return "viewAllVehicles";
		}
	
	@GetMapping("/addVehicle")
	public String addVehicle(Model model) {
		Vehicle v = new Vehicle();
		model.addAttribute("newVehicle", v);
		return "addVehicle";
	}
	
	@PostMapping("/addVehicle")
	public String addVehicle(@ModelAttribute Vehicle v, Model model) {
		repo.save(v);
		return viewAllVehicles(model);
	}
	
	@GetMapping("/editVehicle/{id}")
	public String editVehicle(@PathVariable("id") long id, Model model) {
		Vehicle v = repo.findById(id).orElse(null);
		model.addAttribute("newVehicle", v);
		return "editVehicle";
	}

	@PostMapping("/updateVehicle/{id}")
	public String updateVehicle(Vehicle v, Model model) {
		repo.save(v);
		return viewAllVehicles(model);
	}

	@GetMapping("/deleteVehicle/{id}")
	public String deleteVehicle(@PathVariable("id") long id, Model model) {
		Vehicle v = repo.findById(id).orElse(null);
		repo.delete(v);
		return viewAllVehicles(model);
	}
}
