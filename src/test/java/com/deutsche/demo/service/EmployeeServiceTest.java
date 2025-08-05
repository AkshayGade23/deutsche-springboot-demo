package com.deutsche.demo.service;

import com.deutsche.demo.exception.EmployeeNotFoundException;
import com.deutsche.demo.model.Employee;
import com.deutsche.demo.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeServiceTest {

    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private EmployeeRepository empRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllEmployees() {
        List<Employee> mockList = List.of(new Employee(1, "Sonu", 10000.0));
        when(empRepository.findAll()).thenReturn(mockList);

        List<Employee> result = employeeService.getAllEmployees();
        assertEquals(1, result.size());
    }

    @Test
    void testGetEmployeeByIdSuccess() {
        Employee emp = new Employee(1, "Tonu", 8000.0);
        when(empRepository.findById(1)).thenReturn(Optional.of(emp));

        Employee result = employeeService.getEmployeeById(1);
        assertEquals("Tonu", result.getName());
    }

    @Test
    void testGetEmployeeByIdThrows() {
        when(empRepository.findById(2)).thenReturn(Optional.empty());
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.getEmployeeById(2));
    }

    @Test
    void testGetEmployeesByNameFound() {
        List<Employee> list = List.of(new Employee(3, "Monu", 5000.0));
        when(empRepository.findByName("Monu")).thenReturn(list);

        List<Employee> result = employeeService.getEmployeesByName("Monu");
        assertFalse(result.isEmpty());
    }

    @Test
    void testGetEmployeesByNameNotFound() {
        when(empRepository.findByName("Nonexistent")).thenReturn(Collections.emptyList());
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.getEmployeesByName("Nonexistent"));
    }

    @Test
    void testAddEmployee() {
        Employee emp = new Employee(4, "Ponu", 4500.0);
        when(empRepository.save(emp)).thenReturn(emp);

        Employee result = employeeService.addEmployee(emp);
        assertEquals("Ponu", result.getName());
    }

    @Test
    void testUpdateEmployeeSuccess() {
        Employee existing = new Employee(5, "Old", 7000.0);
        Employee updated = new Employee(5, "Updated", 7500.0);

        when(empRepository.findById(5)).thenReturn(Optional.of(existing));
        when(empRepository.save(any())).thenReturn(updated);

        Employee result = employeeService.updateEmployee(updated);
        assertEquals("Updated", result.getName());
        assertEquals(7500.0, result.getSalary());
    }

    @Test
    void testUpdateEmployeeThrows() {
        Employee updated = new Employee(99, "Ghost", 9999.0);
        when(empRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(EmployeeNotFoundException.class, () -> employeeService.updateEmployee(updated));
    }

    @Test
    void testDeleteEmployeeSuccess() {
        Employee emp = new Employee(10, "DeleteMe", 1000.0);
        when(empRepository.findById(10)).thenReturn(Optional.of(emp));

        Employee deleted = employeeService.deleteEmployee(10);
        verify(empRepository).deleteById(10);
        assertEquals("DeleteMe", deleted.getName());
    }

    @Test
    void testDeleteEmployeeThrows() {
        when(empRepository.findById(100)).thenReturn(Optional.empty());
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.deleteEmployee(100));
    }
}
