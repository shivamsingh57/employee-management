package com.example.project.employee.controller;


import com.example.project.employee.entity.Employee;
import com.example.project.employee.service.EmployeeService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {


    private final EmployeeService employeeService;


    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @PostMapping("/postEmployee")
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeService.postEmployee(employee);
    }

    @GetMapping("/getAllEmployee")
    public List<Employee> getAllEmployee() {
        return employeeService.getAllEmployee();
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        try {
            employeeService.deleteEmployee(id);
            return new ResponseEntity<>("Employee with ID: " + id + " Deleted succesfully", HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/employee/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable Long id) {
        Employee employee = employeeService.getEmployeeById(id);
        if (employee == null) {
            return new ResponseEntity<>("Employee not available", HttpStatus.NOT_FOUND);
        } else {
            return ResponseEntity.ok(employee);
        }
    }


    @PatchMapping("/employee/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        Employee updatedEmployee = employeeService.updateEmployee(id, employee);

        if (updatedEmployee == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.ok(updatedEmployee);
        }

    }


    @PutMapping("/employeeUpById/{}")
    public ResponseEntity<?> updatedEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        Employee updateEmployee = employeeService.updateEmployeeById(id, employee);
        return ResponseEntity.ok(updateEmployee);
    }

}
