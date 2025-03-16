package org.isdb.StudentCRUD.controller;

import java.util.List;

import org.isdb.StudentCRUD.model.Employee;
import org.isdb.StudentCRUD.service.EmployeeService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

	@GetMapping
	public List<Employee> getAllEmp() {
		List<Employee> allEmp = service.getAllEmp();
		return allEmp;
	}

	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable int id) {
		service.deleteById(id);
	}

	@PutMapping("/{id}")
	public Employee updateEmp(@PathVariable int id, @RequestBody Employee employee) {
		Employee updated = service.updateEmp(id, employee);
		return updated;
	}

	@GetMapping("/byName")
	public List<Employee> getAllEmpByName(@RequestParam String name) {
		List<Employee> allEmp = service.getAllEmpByName(name);
		return allEmp;
	}

}
