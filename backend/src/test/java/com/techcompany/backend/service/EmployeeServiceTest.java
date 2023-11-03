package com.techcompany.backend.service;

import com.techcompany.backend.collection.Employee;
import com.techcompany.backend.collection.Gender;
import com.techcompany.backend.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class EmployeeServiceTest {

    private EmployeeService employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        employeeService = new EmployeeService(employeeRepository);
    }

    @Test
    void getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        when(employeeRepository.findAll()).thenReturn(employees);

        List<Employee> result = employeeService.getAllEmployees();

        assertEquals(employees, result);
    }

    @Test
    void saveEmployee() {
        Employee employee = new Employee(
                "1",
                "John",
                "Doe",
                "I'm a software developer with a passion for creating innovative solutions.",
                "john.doe@email.com",
                "1234567890",
                Gender.MALE,
                "San Francisco, USA",
                "Software Developer");
        when(employeeRepository.save(employee)).thenReturn(employee);

        String result = employeeService.saveEmployee(employee);

        assertEquals(employee.getId(), result);
    }

    @Test
    void saveManyEmployees() {
        List<Employee> employees = new ArrayList<>();
        when(employeeRepository.save(any())).thenReturn(new Employee(
                "1",
                "John",
                "Doe",
                "I'm a software developer with a passion for creating innovative solutions.",
                "john.doe@email.com",
                "1234567890",
                Gender.MALE,
                "San Francisco, USA",
                "Software Developer"));

        List<String> result = employeeService.saveManyEmployees(employees);

        assertEquals(employees.size(), result.size());
    }

    @Test
    void getEmployeeById() {
        String id = "1";
        Employee employee = new Employee(
                "1",
                "John",
                "Doe",
                "I'm a software developer with a passion for creating innovative solutions.",
                "john.doe@email.com",
                "1234567890",
                Gender.MALE,
                "San Francisco, USA",
                "Software Developer");
        when(employeeRepository.findById(id)).thenReturn(Optional.of(employee));

        Optional<Employee> result = employeeService.getEmployeeById(id);

        assertEquals(employee, result.orElse(null));
    }

    @Test
    void getEmployeeByEmail() {
        String email = "john.doe@email.com";
        Employee employee = new Employee(
                "1",
                "John",
                "Doe",
                "I'm a software developer with a passion for creating innovative solutions.",
                "john.doe@email.com",
                "1234567890",
                Gender.MALE,
                "San Francisco, USA",
                "Software Developer");
        when(employeeRepository.findEmployeeByEmail(email)).thenReturn(Optional.of(employee));

        Optional<Employee> result = employeeService.getEmployeeByEmail(email);

        assertEquals(employee, result.orElse(null));
    }

    @Test
    void updateEmployee() {
        String id = "1";
        Employee existingEmployee = new Employee(
                "1",
                "John",
                "Doe",
                "I'm a software developer with a passion for creating innovative solutions.",
                "john.doe@email.com",
                "1234567890",
                Gender.MALE,
                "San Francisco, USA",
                "Software Developer");
        when(employeeRepository.findById(id)).thenReturn(Optional.of(existingEmployee));
        Employee partialEmployee = new Employee(
                "1",
                "Johnny",
                "Doe",
                "I'm a software developer with a passion for creating innovative solutions.",
                "john.doe@email.com",
                "1234567890",
                Gender.MALE,
                "San Francisco, USA",
                "Software Developer");
        partialEmployee.setFirstName("Johnny");
        when(employeeRepository.save(existingEmployee)).thenReturn(existingEmployee);

        Optional<Employee> result = employeeService.updateEmployee(id, partialEmployee);

        assertEquals("Johnny", result.orElseThrow().getFirstName());
    }

    @Test
    void deleteEmployee() {
        String id = "1";
        doNothing().when(employeeRepository).deleteById(id);

        employeeService.deleteEmployee(id);

        verify(employeeRepository, times(1)).deleteById(id);
    }

    @Test
    void searchEmployees() {
        String query = "test";
        List<Employee> employees = new ArrayList<>();
        when(employeeRepository.findBySearchCriteria(query)).thenReturn(employees);

        List<Employee> result = employeeService.searchEmployees(query);

        assertEquals(employees, result);
    }
}
