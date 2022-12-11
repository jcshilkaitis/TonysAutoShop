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

import dmacc.beans.Appointments;
import dmacc.beans.Customer;
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
	
	@GetMapping("/addAppointment/{custId}")
	public String addAppointment(@PathVariable("custId") long id,Model model) {
		Customer c = cusRepo.findById(id).orElse(null);
		Appointments a = new Appointments();
		model.addAttribute("customer", c);
		model.addAttribute("newAppointment", a);
		return "addAppointment";
	}
	
	@PostMapping("/addAppointment/{custId}")
	public String addAppointment(@PathVariable("custId") long id, @ModelAttribute Appointments a, Customer c, Model model) {
		a.setCustomer(cusRepo.findById(id).orElse(null));
		repo.save(a);
		c = cusRepo.findById(id).orElse(null);
		List<Appointments> appointment = c.getAppointments();
		appointment.add(a);
		cusRepo.save(c);
		long tempId = a.getCustomer().getId();
		return viewCustomerInformation(tempId,model);
	}
	
	@GetMapping("/editAppointment/{id}")
	public String editAppointment(@PathVariable("id") long id, Model model) {
		Appointments a = repo.findById(id).orElse(null);
		Customer c = a.getCustomer();
		model.addAttribute("customer", c);
		model.addAttribute("newAppointment", a);
		return "editAppointment";
	}

	@PostMapping("/updateAppointment/{id}/{custId}")
	public String updateAppointment(@PathVariable("id") long id, @PathVariable("custId") long custId, Customer c, Appointments a, Model model) {
		c = cusRepo.findById(custId).orElse(null);
		a.setCustomer(c);
		cusRepo.save(c);
		repo.save(a);
		return viewCustomerInformation(c.getId(),model);
	}

	@GetMapping("/deleteAppointment/{id}")
	public String deleteAppointment(@PathVariable("id") long id, Model model) {
		Appointments a = repo.findById(id).orElse(null);
		long tempId = a.getCustomer().getId();
		repo.delete(a);
		return viewCustomerInformation(tempId,model);
	}
	
	public String viewCustomerInformation(@PathVariable("id") long id, Model model) {
		Customer c = cusRepo.findById(id).orElse(null);
		model.addAttribute("customer", c);
		return "viewCustomerInformation";
	}
	

}
