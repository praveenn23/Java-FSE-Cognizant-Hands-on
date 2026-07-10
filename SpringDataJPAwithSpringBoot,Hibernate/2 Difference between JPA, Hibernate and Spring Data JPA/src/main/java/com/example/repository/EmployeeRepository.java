package com.example.repository;

import com.example.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByDepartment(String department);

    List<Employee> findByLastName(String lastName);

    Employee findByEmail(String email);

    List<Employee> findByFirstNameContaining(String keyword);

    List<Employee> findBySalaryGreaterThan(Double salary);

    List<Employee> findByDepartmentAndSalaryGreaterThan(String department, Double salary);
}