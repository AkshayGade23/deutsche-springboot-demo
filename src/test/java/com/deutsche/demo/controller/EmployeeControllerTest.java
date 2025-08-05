package com.deutsche.demo.controller;

import com.deutsche.demo.model.Employee;
import com.deutsche.demo.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeControllerTest {

    @InjectMocks
    private EmployeeController employeeController;

    @Mock
    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);  // Initialize mocks
    }

    @Test
    void testGetAllEmployees() {
        List<Employee> mockList = List.of(new Employee(1, "Sonu", 10000.0));
        when(employeeService.getAllEmployees()).thenReturn(mockList);

        ResponseEntity<List<Employee>> response = employeeController.getAllEmployees();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        assertEquals("Sonu", response.getBody().get(0).getName());
    }

    @Test
    void testGetEmployeeById() {
        Employee emp = new Employee(1, "Monu", 9500.0);
        when(employeeService.getEmployeeById(1)).thenReturn(emp);

        ResponseEntity<Employee> response = employeeController.getEmployeeById(1);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Monu", response.getBody().getName());
    }

    @Test
    void testAddEmployee() {
        Employee emp = new Employee(2, "Tonu", 9000.0);
        when(employeeService.addEmployee(any(Employee.class))).thenReturn(emp);

        ResponseEntity<Employee> response = employeeController.addEmployee(emp);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals("Tonu", response.getBody().getName());
    }

    @Test
    void testUpdateEmployee() {
        Employee emp = new Employee(2, "TonuUpdated", 9500.0);
        when(employeeService.updateEmployee(any(Employee.class))).thenReturn(emp);

        ResponseEntity<Employee> response = employeeController.updateEmployee(emp);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("TonuUpdated", response.getBody().getName());
    }

    @Test
    void testDeleteEmployee() {
        Employee emp = new Employee(3, "DeleteMe", 5000.0);
        when(employeeService.deleteEmployee(3)).thenReturn(emp);

        ResponseEntity<Employee> response = employeeController.deleteEmployee(3);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("DeleteMe", response.getBody().getName());
    }

    @Test
    void testGetEmployeesByName() {
        List<Employee> list = List.of(new Employee(4, "Akshay", 8000.0));
        when(employeeService.getEmployeesByName("Akshay")).thenReturn(list);

        ResponseEntity<List<Employee>> response = employeeController.getEmployeesByName("Akshay");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        assertEquals("Akshay", response.getBody().get(0).getName());
    }

    @Test
    void testGetEmployeesBySalary() {
        List<Employee> list = List.of(new Employee(5, "SameSalary", 7500.0));
        when(employeeService.getEmployeesBySalary(7500.0)).thenReturn(list);

        ResponseEntity<List<Employee>> response = employeeController.getEmployeesBySalary(7500.0);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("SameSalary", response.getBody().get(0).getName());
    }
}
