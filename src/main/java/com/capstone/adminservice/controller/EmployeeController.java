package com.capstone.adminservice.controller;

import com.capstone.adminservice.dto.EmployeeDTO;
import com.capstone.adminservice.entity.Employee;
import com.capstone.adminservice.service.EmployeeService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/employees") // Base URL for all endpoints in this controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // Create a new Employee
    @PostMapping
    public ResponseEntity<String> createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = employeeService.createEmployee(employeeDTO);
        String employeeCreateResponse = "Employee added successfully with id: ";
        return new ResponseEntity<>(employeeCreateResponse + +employee.getId(), HttpStatus.CREATED);
    }

    // Get all Employees
    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        List<EmployeeDTO> employees = employeeService.getAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    // Get an Employee by ID
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
        EmployeeDTO employee = employeeService.getEmployeeById(id);
        if (employee == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    // Delete an Employee by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployeeById(@PathVariable Long id) {
        employeeService.deleteEmployeeById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/addemployees")
    public ResponseEntity<String> addEmployees(@RequestBody List<String> emails){
        employeeService.sendMail(emails);
        return ResponseEntity.ok("credentials sent successfully");
    }


}

