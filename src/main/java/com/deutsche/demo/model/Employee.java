package com.deutsche.demo.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
@Table( name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotNull(message = "Name should be provided")
    @NotBlank(message = "Name should not be empty.")
    @Size(min = 2,max = 50, message = "Name should be in between 2 to 50 characters")
    @Column(name = "name")
    private String name;

    @Positive(message = "Salary should be more than 0")
    @Column(name = "salary")
    private Double salary;

    public Employee() {
    }

    public Employee(Integer id, String name, Double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return id + "\t" + name + "\t" + salary;
    }
}
