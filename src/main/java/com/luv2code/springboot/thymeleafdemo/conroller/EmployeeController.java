package com.luv2code.springboot.thymeleafdemo.conroller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {


	private EmployeeService employeeService;
	
	public EmployeeController (EmployeeService theEmployeeService) {
			employeeService = theEmployeeService;
	}
	
	/// add mapping for list
	@GetMapping("/list")
	public String listEmp(Model theModel) {
	List<Employee> theEmployees= employeeService.findAll();
	theModel.addAttribute("employees", theEmployees);
	
	return "employees/list-employees";
}
	
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		// create a model attribute to bind form data
		Employee theEmployee = new Employee();
		theModel.addAttribute("employee", theEmployee);
		return "employees/employee-form";
	}
	
	@GetMapping("/showFormForUpdate")
	public String ShowFormForUpdate(@RequestParam("employeeId") int theId, Model theModel) {
		
		// get the Employee from the Service
		Employee theEmployee = employeeService.findById(theId);
		
		// set employee as a model attribute to pre populate the form
		theModel.addAttribute("employee", theEmployee);
		
		// send over to our form
		return "employees/employee-form";
		
		
	}
	
	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") Employee theEmployee)
	{
		employeeService.save(theEmployee);
		
		return "redirect:/employees/list";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("employeeId") int theId){
		//delete employee
		Employee theEmployee = employeeService.findById(theId);
		if ( theEmployee == null) {
			throw new RuntimeException("Employee Id no found " + theId);
		}
		employeeService.deleteById(theId);
		return "redirect:/employees/list";
	}
	
}