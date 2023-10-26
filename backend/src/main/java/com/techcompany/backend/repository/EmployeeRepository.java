package com.techcompany.backend.repository;

import com.techcompany.backend.collection.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface EmployeeRepository extends MongoRepository<Employee, String> {

    Optional<Employee> findEmployeeByEmail(String email);
}
