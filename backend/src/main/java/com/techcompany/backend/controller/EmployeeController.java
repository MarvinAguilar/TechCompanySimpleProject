package com.techcompany.backend.controller;

import com.techcompany.backend.collection.Employee;
import com.techcompany.backend.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/techcompany")
@AllArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> fetchAllEmployees() {
        try {
            List<Employee> employees = employeeService.getAllEmployees();
            return ResponseEntity.ok(employees);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<Optional<Employee>> getEmployeeById(@PathVariable String id) {
        try {
            Optional<Employee> employee = employeeService.getEmployeeById(id);
            if (employee.isPresent()) {
                return ResponseEntity.ok(employee);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/employee")
    public ResponseEntity<Optional<Employee>> getEmployeeByEmail(@RequestParam("email") String email) {
        try {
            Optional<Employee> employee = employeeService.getEmployeeByEmail(email);
            if (employee.isPresent()) {
                return ResponseEntity.ok(employee);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/addEmployee")
    public ResponseEntity<String> saveEmployee(@RequestBody Employee employee) {
        try {
            String result = employeeService.saveEmployee(employee);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/addEmployees")
    public ResponseEntity<List<String>> saveEmployees(@RequestBody List<Employee> employees) {
        try {
            List<String> savedEmployeeIds = employeeService.saveManyEmployees(employees);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployeeIds);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PatchMapping("/employee/{id}")
    public ResponseEntity<Optional<Employee>> updateEmployee(@PathVariable String id, @RequestBody Employee partialEmployee) {
        try {
            Optional<Employee> updatedEmployee = employeeService.updateEmployee(id, partialEmployee);
            if (updatedEmployee.isPresent()) {
                return ResponseEntity.ok(updatedEmployee);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable String id) {
        try {
            employeeService.deleteEmployee(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/employee/search")
    public ResponseEntity<List<Employee>> searchEmployees(@RequestParam("query") String query) {
        try {
            List<Employee> employees = employeeService.searchEmployees(query);
            return ResponseEntity.ok(employees);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
