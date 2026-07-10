package com.example.service;

import com.example.entity.Employee;
import com.example.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public List<Employee> saveAllEmployees(List<Employee> employees) {
        return employeeRepository.saveAll(employees);
    }

    public Optional<Employee> findEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    public List<Employee> findByDepartment(String department) {
        return employeeRepository.findByDepartment(department);
    }

    public List<Employee> findByLastName(String lastName) {
        return employeeRepository.findByLastName(lastName);
    }

    public Employee findByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }

    public List<Employee> searchByFirstName(String keyword) {
        return employeeRepository.findByFirstNameContaining(keyword);
    }

    public List<Employee> findHighEarners(Double minSalary) {
        return employeeRepository.findBySalaryGreaterThan(minSalary);
    }

    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        Optional<Employee> existing = employeeRepository.findById(id);
        if (existing.isPresent()) {
            Employee emp = existing.get();
            emp.setFirstName(updatedEmployee.getFirstName());
            emp.setLastName(updatedEmployee.getLastName());
            emp.setEmail(updatedEmployee.getEmail());
            emp.setDepartment(updatedEmployee.getDepartment());
            emp.setSalary(updatedEmployee.getSalary());
            return employeeRepository.save(emp);
        }
        return null;
    }

    public void deleteEmployeeById(Long id) {
        employeeRepository.deleteById(id);
    }

    public long countEmployees() {
        return employeeRepository.count();
    }
}