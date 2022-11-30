package dmacc.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import dmacc.beans.Appointments;
import dmacc.repository.AppointmentRepository;
import dmacc.repository.CustomerRepository;

@Controller
public class AppointmentController {
	@Autowired
	AppointmentRepository repo;
	@Autowired
	CustomerRepository cusRepo;
	
	@GetMapping("/viewAllAppointments")
	public String viewAllAppointments(Model model) {
		model.addAttribute("appointment", repo.findAll());
		return "viewAllAppointments";
		}
	
	@GetMapping("/addAppointment")
	public String addAppointment(Model model) {
		Appointments a = new Appointments();
		repo.save(a);
		model.addAttribute("addAppointment", a);
		return "addAppointment";
	}
	
	@PostMapping("/addAppointment/{id}")
	public String addAppointment(@PathVariable("id") long id, @ModelAttribute Appointments a, Model model) {
		a.setCustomer(cusRepo.findById(id).orElse(null));
		repo.save(a);
		return "viewAllCustomers";
	}
	
	@GetMapping("/editAppointment/{id}")
	public String editAppointment(@PathVariable("id") long id, Model model) {
		Appointments a = repo.findById(id).orElse(null);
		model.addAttribute("newAppointment", a);
		return "editAppointment";
	}

	@PostMapping("/updateAppointment/{id}")
	public String updateAppointment(Appointments a, Model model) {
		repo.save(a);
		return viewAllAppointments(model);
	}

	@GetMapping("/deleteAppointment/{id}")
	public String deleteAppointment(@PathVariable("id") long id, Model model) {
		Appointments a = repo.findById(id).orElse(null);
		repo.delete(a);
		return viewAllAppointments(model);
	}
}
