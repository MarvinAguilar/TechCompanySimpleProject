package com.techcompany.backend.controller;

import com.techcompany.backend.collection.Employee;
import com.techcompany.backend.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/techcompany")
@AllArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> fetchAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/employee/{id}")
    public Optional<Employee> getEmployeeById(@PathVariable String id) {
        return employeeService.getEmployeeById(id);
    }

    @GetMapping("/employee")
    public Optional<Employee> getEmployeeByEmail(@RequestParam("email") String email) {
        return employeeService.getEmployeeByEmail(email);
    }

    @PostMapping("/addEmployee")
    public String saveEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    @PatchMapping("/employee/{id}")
    public Optional<Employee> updateEmployee(@PathVariable String id, @RequestBody Employee partialEmployee) {
        return employeeService.updateEmployee(id, partialEmployee);
    }

    @DeleteMapping("/employee/{id}")
    public void deleteEmployee(@PathVariable String id) {
        employeeService.deleteEmployee(id);
    }
}
