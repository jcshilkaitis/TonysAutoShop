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
import dmacc.repository.CustomerRepository;

@Controller
public class CustomerController {
	@Autowired
	CustomerRepository repo;
	
	@GetMapping("/viewAllCustomers")
	public String viewAllCustomers(Model model) {
		model.addAttribute("customer", repo.findAll());
		return "viewAllCustomers";
	}

	@GetMapping(path = "/customer")
	public String viewCustomers (Model model){
		
		List<Customer> customerList = new ArrayList<>();
		customerList = repo.findAll();
		model.addAttribute("customerList", customerList);
		return "index";
	}
	
	@GetMapping("/addCustomer")
	public String addCustomer(Model model) {
		Customer c = new Customer();
		model.addAttribute("addCustomer", c);
		return "addCustomer";
	}
	
	@PostMapping("/addCustomer")
	public String addCustomer(@ModelAttribute Customer c, Model model) {
		repo.save(c);
		return viewAllCustomers(model);
	}
	
	@GetMapping("/editCustomer/{id}")
	public String editCustomer(@PathVariable("id") long id, Model model) {
		Customer c = repo.findById(id).orElse(null);
		model.addAttribute("newCustomer", c);
		return "editCustomer";
	}

	@PostMapping("/updateCustomer/{id}")
	public String updateCustomer(Customer c, Model model) {
		repo.save(c);
		return viewAllCustomers(model);
	}

	@GetMapping("/deleteCustomer/{id}")
	public String deleteCustomer(@PathVariable("id") long id, Model model) {
		Customer c = repo.findById(id).orElse(null);
		repo.delete(c);
		return viewAllCustomers(model);
	}
}
