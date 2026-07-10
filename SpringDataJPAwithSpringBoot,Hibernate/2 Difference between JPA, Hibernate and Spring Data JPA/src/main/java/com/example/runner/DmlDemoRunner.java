package com.example.runner;

import com.example.entity.Employee;
import com.example.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class DmlDemoRunner implements CommandLineRunner {

    @Autowired
    private EmployeeService employeeService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("\n================================================================");
        System.out.println("  SPRING DATA JPA - DML Operations Demo");
        System.out.println("  Difference between JPA, Hibernate and Spring Data JPA");
        System.out.println("================================================================\n");

        System.out.println("--- 1. CREATE: Inserting employees using JpaRepository.save() ---");
        System.out.println("    [With raw Hibernate, this would need SessionFactory, Session,");
        System.out.println("     Transaction setup. With Spring Data JPA, just call save()!]\n");

        List<Employee> employees = Arrays.asList(
            new Employee("John", "Doe", "john.doe@company.com", "IT", 75000.0),
            new Employee("Jane", "Smith", "jane.smith@company.com", "HR", 68000.0),
            new Employee("Bob", "Johnson", "bob.johnson@company.com", "IT", 82000.0),
            new Employee("Alice", "Williams", "alice.williams@company.com", "Finance", 71000.0),
            new Employee("Charlie", "Brown", "charlie.brown@company.com", "IT", 90000.0)
        );

        List<Employee> savedEmployees = employeeService.saveAllEmployees(employees);
        savedEmployees.forEach(emp -> System.out.println("  Saved: " + emp));
        System.out.println("  Total employees inserted: " + employeeService.countEmployees());

        System.out.println("\n--- 2a. READ: JpaRepository.findById(1) ---");
        Optional<Employee> empById = employeeService.findEmployeeById(1L);
        empById.ifPresent(e -> System.out.println("  Found: " + e));

        System.out.println("\n--- 2b. READ: JpaRepository.findAll() ---");
        List<Employee> allEmployees = employeeService.findAllEmployees();
        allEmployees.forEach(e -> System.out.println("  " + e));

        System.out.println("\n--- 2c. READ: Derived Query Method - findByDepartment('IT') ---");
        System.out.println("    [Spring Data JPA auto-generates: SELECT * FROM employees WHERE department = 'IT']");
        List<Employee> itEmployees = employeeService.findByDepartment("IT");
        itEmployees.forEach(e -> System.out.println("  " + e));

        System.out.println("\n--- 2d. READ: Derived Query Method - findByLastName('Smith') ---");
        List<Employee> smiths = employeeService.findByLastName("Smith");
        smiths.forEach(e -> System.out.println("  " + e));

        System.out.println("\n--- 2e. READ: Derived Query Method - findByEmail() ---");
        Employee byEmail = employeeService.findByEmail("bob.johnson@company.com");
        System.out.println("  Found: " + byEmail);

        System.out.println("\n--- 2f. READ: Derived Query Method - findBySalaryGreaterThan(80000) ---");
        List<Employee> highEarners = employeeService.findHighEarners(80000.0);
        highEarners.forEach(e -> System.out.println("  " + e));

        System.out.println("\n--- 3. UPDATE: JpaRepository.save() with existing entity ---");
        System.out.println("    [When entity has an ID, Hibernate calls merge() instead of persist()]");

        Employee updatedData = new Employee("John", "Doe", "john.doe@company.com", "IT", 85000.0);
        Employee updatedEmp = employeeService.updateEmployee(1L, updatedData);
        System.out.println("  Updated: " + updatedEmp);

        System.out.println("\n--- 4. DELETE: JpaRepository.deleteById(5) ---");
        System.out.println("  Before delete - Total: " + employeeService.countEmployees());
        employeeService.deleteEmployeeById(5L);
        System.out.println("  After delete  - Total: " + employeeService.countEmployees());

        System.out.println("\n  Remaining employees:");
        employeeService.findAllEmployees().forEach(e -> System.out.println("    " + e));

        System.out.println("\n================================================================");
        System.out.println("  KEY TAKEAWAYS:");
        System.out.println("  - JPA: Specification only (defines annotations, interfaces)");
        System.out.println("  - Hibernate: ORM implementation (does the actual work)");
        System.out.println("  - Spring Data JPA: Abstraction (removes boilerplate code)");
        System.out.println("================================================================");
        System.out.println("  H2 Console: http://localhost:8080/h2-console");
        System.out.println("  JDBC URL: jdbc:h2:mem:employeedb");
        System.out.println("================================================================\n");
    }
}