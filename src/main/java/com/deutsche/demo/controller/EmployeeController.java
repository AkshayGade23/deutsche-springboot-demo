package com.deutsche.demo.controller;


import com.deutsche.demo.model.Employee;
import com.deutsche.demo.service.EmployeeService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:5173")
//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("employees")
public class EmployeeController {


    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EmployeeService employeeService;


    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        LOG.info("Fetching all employees...");
        List<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok().header("message", "List of all employees returned successfully.").body(employees);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@Valid @PathVariable(name = "id") Integer id) {
        LOG.info("Fetching employee by ID: {}", id);
        Employee employee = employeeService.getEmployeeById(id);

        return ResponseEntity.ok().header("message", "Employee with the id " + id + "return successfully.").body(employee);
    }


    @GetMapping(value = "/search", params = "name")
    public ResponseEntity<List<Employee>> getEmployeesByName(@RequestParam String name) {
        LOG.info("Searching employee by name: {}", name);
        List<Employee> employees = employeeService.getEmployeesByName(name);
        return ResponseEntity.ok().header("message", "Employees with name '" + name + "' returned successfully.").body(employees);
    }


    @GetMapping(value = "/search", params = "salary")
    public ResponseEntity<List<Employee>> getEmployeesBySalary(@RequestParam Double salary) {
        LOG.info("Searching employee by salary: {}", salary);
        List<Employee> employees = employeeService.getEmployeesBySalary(salary);
        return ResponseEntity.ok().header("message", "Employees with salary '" + salary + "' returned successfully.").body(employees);
    }


    @PostMapping
    public ResponseEntity<Employee> addEmployee(@Valid @RequestBody Employee employee) {
        LOG.info("Adding new employee: {}", employee);
        Employee savedEmployee = employeeService.addEmployee(employee);
        return ResponseEntity.status(HttpStatus.CREATED).header("message", "New employee added successfully.").body(savedEmployee);
    }


    @PutMapping
    public ResponseEntity<Employee> updateEmployee(@Valid @RequestBody Employee employee) {
        LOG.info("Updating employee: {}", employee);
        Employee updatedEmployee = employeeService.updateEmployee(employee);
        return ResponseEntity.ok().header("message", "Employee updated successfully.").body(updatedEmployee);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Employee> deleteEmployee(@Valid @PathVariable Integer id) {
        LOG.info("Deleting employee with ID: {}", id);
        Employee deletedEmployee = employeeService.deleteEmployee(id);
        return ResponseEntity.ok().header("message", "Employee with ID " + id + " deleted successfully.").body(deletedEmployee);
    }

}
