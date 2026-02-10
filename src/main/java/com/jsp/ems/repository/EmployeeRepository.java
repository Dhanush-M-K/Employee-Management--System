package com.jsp.ems.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jsp.ems.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
	
	

	Optional<Employee> findByEmail(String email);
	Optional<Employee> findByName(String name);
}
