package com.techcompany.backend.service;

import com.techcompany.backend.collection.Employee;
import com.techcompany.backend.excepction.*;
import com.techcompany.backend.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeService implements IEmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAllEmployees() {
        try {
            return employeeRepository.findAll();
        } catch (Exception e) {
            throw new EmployeeNotFoundException("Error retrieving the list of employees", e);
        }
    }

    @Override
    public String saveEmployee(Employee employee) {
        try {
            return employeeRepository.save(employee).getId();
        } catch (Exception e) {
            throw new EmployeeSaveException("Error saving the employee", e);
        }
    }

    @Override
    public List<String> saveManyEmployees(List<Employee> employees) {
        try {
            List<String> savedEmployeeIds = new ArrayList<>();

            for (Employee employee : employees) {
                String employeeId = employeeRepository.save(employee).getId();
                savedEmployeeIds.add(employeeId);
            }

            return savedEmployeeIds;
        } catch (Exception e) {
            throw new EmployeeSaveException("Error saving employees", e);
        }
    }

    @Override
    public Optional<Employee> getEmployeeById(String id) {
        try {
            return Optional.ofNullable(employeeRepository.findById(id)
                    .orElseThrow(() -> new EmployeeNotFoundException("Employee with ID " + id + " not found")));
        } catch (Exception e) {
            throw new EmployeeNotFoundException("Error looking up employee by ID", e);
        }
    }

    @Override
    public Optional<Employee> getEmployeeByEmail(String email) {
        try {
            return employeeRepository.findEmployeeByEmail(email);
        } catch (Exception e) {
            throw new EmployeeNotFoundException("Error looking up employee by email", e);
        }
    }

    @Override
    public Optional<Employee> updateEmployee(String id, Employee partialEmployee) {
        try {
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
                    .orElseThrow(() -> new EmployeeNotFoundException("Employee with ID " + id + " not found")));
        } catch (Exception e) {
            throw new EmployeeUpdateException("Error updating the employee", e);
        }
    }

    @Override
    public void deleteEmployee(String id) {
        try {
            employeeRepository.deleteById(id);
        } catch (Exception e) {
            throw new EmployeeDeleteException("Error deleting the employee", e);
        }
    }

    @Override
    public List<Employee> searchEmployees(String query) {
        try {
            return employeeRepository.findBySearchCriteria(query);
        } catch (Exception e) {
            throw new EmployeeSearchException("Error searching employees by search criteria", e);
        }
    }
}
