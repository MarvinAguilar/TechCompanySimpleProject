package com.techcompany.backend.service;

import com.techcompany.backend.collection.Employee;
import com.techcompany.backend.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeService implements IEmployeeService {

    private final EmployeeRepository employeeRepository;
    private final MongoTemplate mongoTemplate;

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public String saveEmployee(Employee employee) {
        return employeeRepository.save(employee).getId();
    }

    @Override
    public Optional<Employee> getEmployeeById(String id) {
        return employeeRepository.findById(id);
    }

    @Override
    public Optional<Employee> getEmployeeByEmail(String email) {
        return employeeRepository.findEmployeeByEmail(email);
    }

    @Override
    public Optional<Employee> updateEmployee(String id, Employee partialEmployee) {
        return Optional.ofNullable(employeeRepository.findById(id)
                .map(existingEmployee -> {
                    if (partialEmployee.getFirstName() != null) {
                        existingEmployee.setFirstName(partialEmployee.getFirstName());
                    }

                    if (partialEmployee.getLastName() != null) {
                        existingEmployee.setLastName(partialEmployee.getLastName());
                    }

                    if (partialEmployee.getAbout() != null) {
                        existingEmployee.setAbout(partialEmployee.getAbout());
                    }

                    if (partialEmployee.getPhoneNumber() != null) {
                        existingEmployee.setPhoneNumber(partialEmployee.getPhoneNumber());
                    }

                    if (partialEmployee.getGender() != null) {
                        existingEmployee.setGender(partialEmployee.getGender());
                    }

                    if (partialEmployee.getLocation() != null) {
                        existingEmployee.setLocation(partialEmployee.getLocation());
                    }

                    if (partialEmployee.getJobPosition() != null) {
                        existingEmployee.setJobPosition(partialEmployee.getJobPosition());
                    }

                    return employeeRepository.save(existingEmployee);
                })
                .orElseThrow(() -> new IllegalStateException("Employee with id " + id + " not found")));
    }

    @Override
    public void deleteEmployee(String id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public List<Employee> searchEmployees(String query) {
        return employeeRepository.findBySearchCriteria(query);
    }
}
