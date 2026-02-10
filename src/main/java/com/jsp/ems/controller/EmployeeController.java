package com.jsp.ems.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.ems.entity.Employee;
import com.jsp.ems.service.EmployeeService;
import com.jsp.ems.util.ResponseStructure;

import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("employee")
public class EmployeeController {
	private EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Employee>> saveEmployee(@RequestBody Employee employee) {
		return employeeService.saveEmployee(employee);
	}
	@GetMapping("/id/{id}")
	public ResponseEntity<ResponseStructure<Employee>> fetchById(@PathVariable int id) {
		return employeeService.fetchById(id);
	}
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Employee>>> fechAll() {
		return employeeService.fetchAll();
	}
	@PutMapping("/{id}")
	public ResponseEntity<ResponseStructure<Employee>> updateEmployee(@PathVariable int id, @RequestBody Employee employee) {
		return employeeService.updateEmployee(id, employee);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<Employee>> deleteEmployee(@PathVariable int id) {
		return employeeService.deleteEmployee(id);
	}
	@GetMapping("email/{email}")
	public ResponseEntity<ResponseStructure<Employee>> findByEmail(@PathVariable String email) {
		return employeeService.findByEmail(email);
	}
	@GetMapping("name/{name}")
	public ResponseEntity<ResponseStructure<Employee>> findByName(@PathVariable String name) {
		return employeeService.findByName(name);
	}
}
