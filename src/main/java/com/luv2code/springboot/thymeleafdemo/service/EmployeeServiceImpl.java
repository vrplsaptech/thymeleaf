package com.luv2code.springboot.thymeleafdemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luv2code.springboot.thymeleafdemo.dao.EmployeeRepository;
import com.luv2code.springboot.thymeleafdemo.entity.Employee;
@Service
public class EmployeeServiceImpl implements EmployeeService {
	//private EmployeeDAO employeeDAO;
	private EmployeeRepository employeeRepository;
	@Autowired
	//public EmployeeServiceImpl(@Qualifier("employeeDAOHibernateImpl") EmployeeDAO theEmployeeDAO) {
	//public EmployeeServiceImpl(@Qualifier("employeeDAOJpaImpl") EmployeeDAO theEmployeeDAO) {
		public EmployeeServiceImpl( EmployeeRepository theEmployeeRepository) {
		
		employeeRepository=theEmployeeRepository;
	}
	
	@Override
	public List<Employee> findAll() {
		// TODO Auto-generated method stub
	//	return employeeRepository.findAll();
		return employeeRepository.findAllByOrderByFirstNameAsc();
		
	}
	

	@Override

	public void save(Employee theEmployee) {
		employeeRepository.save(theEmployee);

	}

	@Override

	public void deleteById(int theId) {
		employeeRepository.deleteById(theId);

	}

	@Override

	public Employee findById(int theId) {
		// TODO Auto-generated method stub
		Optional<Employee> result = employeeRepository.findById(theId);
		
		Employee theEmployee = null;
		if(result.isPresent()) {
		theEmployee=  result.get();
		}
		else
		{
			throw new RuntimeException("Dind not  find Employee ID :"  + theId);
		}
return theEmployee;
}
}
