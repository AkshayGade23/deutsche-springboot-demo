package com.deutsche.demo.exception;

public class EmployeeNotFoundException extends RuntimeException{

    public EmployeeNotFoundException(Integer id){
        super("Employee with id " + id + " not found.");
    }

    public EmployeeNotFoundException(String name){
        super("Employee with name '" + name + "' not found.");
    }

    public EmployeeNotFoundException(Double salary){
        super("Employee with salary '" + salary + "' not found.");
    }
}
