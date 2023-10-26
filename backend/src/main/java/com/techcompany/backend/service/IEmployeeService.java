package com.techcompany.backend.service;

import com.techcompany.backend.collection.Employee;

import java.util.List;
import java.util.Optional;

public interface IEmployeeService {
    public List<Employee> getAllEmployees();
    public String saveEmployee(Employee employee);
    Optional<Employee> getEmployeeById(String id);
    Optional<Employee> getEmployeeByEmail(String email);
    Optional<Employee> updateEmployee(String id, Employee partialEmployee);
    void deleteEmployee(String id);
}
