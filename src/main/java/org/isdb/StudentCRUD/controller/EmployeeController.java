package org.isdb.StudentCRUD.controller;

import org.isdb.StudentCRUD.model.Employee;
import org.isdb.StudentCRUD.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {

	private EmployeeService service;

	public EmployeeController(EmployeeService service) {
		this.service = service;
	}

	@PostMapping
	public Employee saveEmp(@RequestBody Employee employee) {
		Employee savedEmp = service.saveEmployee(employee);
		return savedEmp;
	}

	@GetMapping("/{id}")
	public Employee getEmpById(@PathVariable int id) {
		Employee empById = service.getEmpById(id);
		return empById;
	}

}
