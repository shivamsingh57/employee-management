package com.example.project.employee.service;

import com.example.project.employee.entity.Employee;
import com.example.project.employee.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    public Employee postEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }


    public void deleteEmployee(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new EntityNotFoundException("Employee with ID: " + id + "Not Found");
        }
        employeeRepository.deleteById(id);
    }


    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }


    public Employee updateEmployee(Long id, Employee employee) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);

        if (optionalEmployee.isPresent()) {
            Employee existingEmployee = optionalEmployee.get();

            existingEmployee.setName(employee.getName());
            existingEmployee.setEmail(employee.getEmail());
            existingEmployee.setPhone(employee.getDepartment());
            existingEmployee.setDepartment(employee.getDepartment());

            return employeeRepository.save(existingEmployee);
            //this is the layer for update employee
        }
        return null;
    }


    public Employee updateEmployeeById(Long id, Employee employee) {
        employee.setId(id);
        employeeRepository.save(employee);
        return employee;
    }


}
