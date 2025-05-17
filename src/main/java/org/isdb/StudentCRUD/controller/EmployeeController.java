package org.isdb.StudentCRUD.controller;

import org.isdb.StudentCRUD.model.Employee;
import org.isdb.StudentCRUD.service.EmployeeService;
import org.isdb.StudentCRUD.service.FileStorageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {

    private final EmployeeService service;
    private final FileStorageService storageService;

    public EmployeeController(EmployeeService service,
                              FileStorageService storageService) {
        this.service = service;
        this.storageService = storageService;
    }

    @PostMapping
    public Employee saveEmp(@RequestBody Employee employee) {
        return service.saveEmployee(employee);
    }

    @PostMapping("/{employeeId}/upload")
    public ResponseEntity<?> uploadFile(@PathVariable Integer employeeId,
                                        @RequestParam("file") MultipartFile file) {
        try {
            String savedFileName = storageService.storeFile(file);

            String accessUrl = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/imageurl/")
                    .path(savedFileName)
                    .toUriString();

            Employee empById = service.getEmpById(employeeId);
            empById.setImage(accessUrl);

            service.updateEmp(employeeId, empById);

            return ResponseEntity.ok(Map.of("message", "File uploaded", "url", accessUrl));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", e.getMessage()));
        }
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
