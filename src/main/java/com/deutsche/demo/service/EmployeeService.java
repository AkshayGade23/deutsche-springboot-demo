package com.deutsche.demo.service;

import com.deutsche.demo.exception.EmployeeNotFoundException;
import com.deutsche.demo.model.Employee;
import com.deutsche.demo.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    @Autowired
    EmployeeRepository empRepository;


    public List<Employee> getAllEmployees() {
        return empRepository.findAll();
    }


    public Employee getEmployeeById(Integer id) {
        Optional<Employee> employee = empRepository.findById(id);
        if(employee.isPresent()) {
            return employee.get();
        }
       else {
           LOG.warn("Employee with ID {} not found.", id);
           throw new EmployeeNotFoundException(id);
        }
    }


    public List<Employee> getEmployeesByName(String name) {
        LOG.info("Finding employee with name {}", name);
        List<Employee> employees = empRepository.findByName(name);
        if (employees.isEmpty()) {
            LOG.error("No employee found with name '{}'", name);
            throw new EmployeeNotFoundException(name);
        }
        return employees;
    }


    public List<Employee> getEmployeesBySalary(Double salary) {
        LOG.info("Finding employee with salary {}", salary);
        List<Employee> employees = empRepository.findBySalary(salary);
        if (employees.isEmpty()) {
            LOG.error("No employee found with salary '{}'", salary);
            throw new EmployeeNotFoundException(salary);
        }
        return employees;
    }

    public Employee addEmployee(Employee employee) {
        return empRepository.save(employee);
    }


    public Employee updateEmployee(Employee emp) {
        Optional<Employee> existing = empRepository.findById(emp.getId());
        if (existing.isPresent()) {
            LOG.info("Employee found. Updating...");
           Employee existingEmp = existing.get();
           if(emp.getName()!=null) {
               existingEmp.setName(emp.getName());
           }

           if(emp.getSalary() != 0.0){
               existingEmp.setSalary(emp.getSalary());
           }
            return empRepository.save(existingEmp);
        } else {
            LOG.error("Cannot update. Employee with ID {} not found.", emp.getId());
            throw new EmployeeNotFoundException(emp.getId());
        }
    }


    public Employee deleteEmployee(Integer id) {
        Optional<Employee> employee = empRepository.findById(id);
        if (employee.isPresent()) {
            empRepository.deleteById(id);
            LOG.info("Employee with ID {} deleted successfully.", id);
            return employee.get();
        } else {
            LOG.error("Cannot delete. Employee with ID {} not found.", id);
            throw new EmployeeNotFoundException(id);
        }
    }
}
