package com.techcompany.backend.controller;

import com.techcompany.backend.collection.Employee;
import com.techcompany.backend.collection.Gender;
import com.techcompany.backend.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class EmployeeControllerTest {

    private EmployeeController employeeController;

    @Mock
    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        employeeController = new EmployeeController(employeeService);
    }

    @Test
    void fetchAllEmployees() {
        List<Employee> employees = List.of(new Employee(
                        "1",
                        "John",
                        "Doe",
                        "I'm a software developer with a passion for creating innovative solutions.",
                        "john.doe@email.com",
                        "1234567890",
                        Gender.MALE,
                        "San Francisco, USA",
                        "Software Developer"),
                new Employee(
                        "2",
                        "Jane",
                        "Smith",
                        "I'm a software engineer specializing in web development.",
                        "jane.smith@email.com",
                        "9876543210",
                        Gender.FEMALE,
                        "New York, USA",
                        "Web Developer"
                ));
        when(employeeService.getAllEmployees()).thenReturn(employees);

        ResponseEntity<List<Employee>> response = employeeController.fetchAllEmployees();

        assertEquals(employees, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getEmployeeByIdFound() {
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
        when(employeeService.getEmployeeById(id)).thenReturn(Optional.of(employee));

        ResponseEntity<Optional<Employee>> response = employeeController.getEmployeeById(id);

        assertEquals(employee, Objects.requireNonNull(response.getBody()).orElse(null));
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getEmployeeByIdNotFound() {
        String id = "1";
        when(employeeService.getEmployeeById(id)).thenReturn(Optional.empty());

        ResponseEntity<Optional<Employee>> response = employeeController.getEmployeeById(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void getEmployeeByEmailFound() {
        String email = "test@example.com";
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
        when(employeeService.getEmployeeByEmail(email)).thenReturn(Optional.of(employee));

        ResponseEntity<Optional<Employee>> response = employeeController.getEmployeeByEmail(email);

        assertEquals(employee, Objects.requireNonNull(response.getBody()).orElse(null));
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getEmployeeByEmailNotFound() {
        String email = "test@example.com";
        when(employeeService.getEmployeeByEmail(email)).thenReturn(Optional.empty());

        ResponseEntity<Optional<Employee>> response = employeeController.getEmployeeByEmail(email);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
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
        String id = "1";
        when(employeeService.saveEmployee(employee)).thenReturn(id);

        ResponseEntity<String> response = employeeController.saveEmployee(employee);

        assertEquals(id, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void saveEmployees() {
        List<Employee> employees = List.of(new Employee(
                "1",
                "John",
                "Doe",
                "I'm a software developer with a passion for creating innovative solutions.",
                "john.doe@email.com",
                "1234567890",
                Gender.MALE,
                "San Francisco, USA",
                "Software Developer"), new Employee(
                "2",
                "Jane",
                "Smith",
                "I'm a software engineer specializing in web development.",
                "jane.smith@email.com",
                "9876543210",
                Gender.FEMALE,
                "New York, USA",
                "Web Developer"
        ));
        List<String> savedIds = List.of("1", "2");
        when(employeeService.saveManyEmployees(employees)).thenReturn(savedIds);

        ResponseEntity<List<String>> response = employeeController.saveEmployees(employees);

        assertEquals(savedIds, response.getBody());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void updateEmployeeFound() {
        String id = "1";
        Employee partialEmployee = new Employee(
                "1",
                "John",
                "Doe",
                "I'm a software developer with a passion for creating innovative solutions.",
                "john.doe@email.com",
                "1234567890",
                Gender.MALE,
                "San Francisco, USA",
                "Software Developer");
        Employee updatedEmployee = new Employee(
                "1",
                "John",
                "Doe",
                "Hello World!",
                "john.doe@email.com",
                "987456123",
                Gender.MALE,
                "San Francisco, USA",
                "Software Developer");
        when(employeeService.updateEmployee(id, partialEmployee)).thenReturn(Optional.of(updatedEmployee));

        ResponseEntity<Optional<Employee>> response = employeeController.updateEmployee(id, partialEmployee);

        assertEquals(updatedEmployee, Objects.requireNonNull(response.getBody()).orElse(null));
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void updateEmployeeNotFound() {
        String id = "1";
        Employee partialEmployee = new Employee(
                "1",
                "John",
                "Doe",
                "I'm a software developer with a passion for creating innovative solutions.",
                "john.doe@email.com",
                "1234567890",
                Gender.MALE,
                "San Francisco, USA",
                "Software Developer");
        when(employeeService.updateEmployee(id, partialEmployee)).thenReturn(Optional.empty());

        ResponseEntity<Optional<Employee>> response = employeeController.updateEmployee(id, partialEmployee);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void deleteEmployee() {
        String id = "1";

        ResponseEntity<Void> response = employeeController.deleteEmployee(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(employeeService, times(1)).deleteEmployee(id);
    }

    @Test
    void searchEmployees() {
        String query = "John";
        List<Employee> employees = List.of(new Employee(
                "1",
                "John",
                "Doe",
                "I'm a software developer with a passion for creating innovative solutions.",
                "john.doe@email.com",
                "1234567890",
                Gender.MALE,
                "San Francisco, USA",
                "Software Developer"), new Employee(
                "2",
                "Jane",
                "Smith",
                "I'm a software engineer specializing in web development.",
                "jane.smith@email.com",
                "9876543210",
                Gender.FEMALE,
                "New York, USA",
                "Web Developer"
        ));
        when(employeeService.searchEmployees(query)).thenReturn(employees);

        ResponseEntity<List<Employee>> response = employeeController.searchEmployees(query);

        assertEquals(employees, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
