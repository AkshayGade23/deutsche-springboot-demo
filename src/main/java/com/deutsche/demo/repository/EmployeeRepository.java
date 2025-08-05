package com.deutsche.demo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.deutsche.demo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

    List<Employee> findByName(String name);

    @Query("SELECT e FROM Employee e WHERE e.salary = :salary")
    List<Employee> findBySalary(@Param("salary") Double salary);
}
