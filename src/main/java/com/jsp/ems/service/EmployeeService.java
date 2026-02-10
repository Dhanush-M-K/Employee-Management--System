package com.jsp.ems.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.ems.entity.Employee;
import com.jsp.ems.exception.EmployeeNotFoundException;
import com.jsp.ems.repository.EmployeeRepository;
import com.jsp.ems.util.ResponseStructure;

@Service
public class EmployeeService {
	
	private EmployeeRepository employeeRepository;
	
	public EmployeeService(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}

	public ResponseEntity<ResponseStructure<Employee>> saveEmployee(Employee employee) {
		Employee emp = employeeRepository.save(employee);
		ResponseStructure<Employee> responseStructure = new ResponseStructure<Employee>(HttpStatus.CREATED.value(), "Successfully Inserted", emp);
		return	 new ResponseEntity< ResponseStructure<Employee> >(responseStructure,HttpStatus.CREATED);

	}
	
	public ResponseEntity<ResponseStructure<Employee>> fetchById(int id) {
		Optional<Employee> optional = employeeRepository.findById(id);
		if(optional.isPresent()) {
			Employee employee = optional.get();
			ResponseStructure<Employee> responseStructure = new ResponseStructure<Employee>(HttpStatus.OK.value(), "User Found", employee);
			return	 new ResponseEntity< ResponseStructure<Employee> >(responseStructure,HttpStatus.OK);
		}
		else {
		 throw new EmployeeNotFoundException("The person with id "+id+" is not present");
		}
	}
	public ResponseEntity<ResponseStructure<List<Employee>>> fetchAll() {
		 List<Employee> list = employeeRepository.findAll();
		 ResponseStructure<List<Employee>> responseStructure = new ResponseStructure<List<Employee>>(HttpStatus.OK.value(), "Users Found", list);
			return	 new ResponseEntity< ResponseStructure<List<Employee>> >(responseStructure,HttpStatus.OK);
		 
	}
	
	public ResponseEntity<ResponseStructure<Employee>> updateEmployee(int id,Employee employee) {
		Optional<Employee> optional = employeeRepository.findById(id);
		if(optional.isPresent()) {
			employee.setId(id);
			 Employee emp = employeeRepository.save(employee);
			 ResponseStructure<Employee> responseStructure = new ResponseStructure<Employee>(HttpStatus.OK.value(), " Updated successfully", emp);
				return	 new ResponseEntity< ResponseStructure<Employee>>(responseStructure,HttpStatus.OK);
		}
		else {
			throw new EmployeeNotFoundException("The person with id "+id+" is not present");
		}
		
	}
	
	public ResponseEntity<ResponseStructure<Employee>> deleteEmployee(int id) {
	    Optional<Employee> optional = employeeRepository.findById(id);
	    if (optional.isPresent()) {
	        Employee employee = optional.get();
	        employeeRepository.delete(employee);
	        ResponseStructure<Employee> responseStructure = new ResponseStructure<Employee>(HttpStatus.OK.value(), " Deleted successfully", employee);
			return	 new ResponseEntity< ResponseStructure<Employee>>(responseStructure,HttpStatus.OK);
	    }
	    else {
	    	throw new EmployeeNotFoundException("The person with id "+id+" is not present");
	    }
	}
	public ResponseEntity<ResponseStructure<Employee>> findByEmail(String email) {
		Optional<Employee> optional = employeeRepository.findByEmail(email);
		if(optional.isPresent()) {
			 Employee employee = optional.get();
			 ResponseStructure<Employee> responseStructure = new ResponseStructure<Employee>(HttpStatus.OK.value(), "User Found", employee);
				return	 new ResponseEntity< ResponseStructure<Employee> >(responseStructure,HttpStatus.OK);
		}
		else {
		throw new EmployeeNotFoundException("The person with email "+email+" is not present");
		}
		
	}
	public ResponseEntity<ResponseStructure<Employee>> findByName(String name) {
		Optional<Employee> optional = employeeRepository.findByName(name);
		if(optional.isPresent()) {
			Employee employee = optional.get();
			 ResponseStructure<Employee> responseStructure = new ResponseStructure<Employee>(HttpStatus.OK.value(), "User Found", employee);
				return	 new ResponseEntity< ResponseStructure<Employee> >(responseStructure,HttpStatus.OK);
			
		}
		throw new EmployeeNotFoundException("The person with name "+name+" is not present");
		
	}

}
