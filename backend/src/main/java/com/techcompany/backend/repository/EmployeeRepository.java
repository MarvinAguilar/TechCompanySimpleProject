package com.techcompany.backend.repository;

import com.techcompany.backend.collection.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends MongoRepository<Employee, String> {

    Optional<Employee> findEmployeeByEmail(String email);

    @Query("{ $or: [ " +
            "{ 'id': ?0 }, " +
            "{ $expr: { $regexMatch: { input: { $concat: [ '$firstName', ' ', '$lastName' ] }, regex: ?0, options: 'i' } } }, " +
            "{ 'email': { $regex: ?0, $options: 'i' } }, " +
            "{ 'jobPosition': { $regex: ?0, $options: 'i' } }, " +
            "{ 'gender': { $regex: ?0, $options: 'i' } }, " +
            "{ 'location': { $regex: ?0, $options: 'i' } }, " +
            "] }")
    List<Employee> findBySearchCriteria(String query);
}
