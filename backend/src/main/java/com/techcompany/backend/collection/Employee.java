package com.techcompany.backend.collection;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@Document(collection = "employees")
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Employee {
    @Id
    @Field("_id")
    private String id;
    private String firstName;
    private String lastName;
    @Indexed(unique = true)
    private String email;
    private String phoneNumber;
    private Gender gender;
    private String location;
    private List<String> jobPositions;

    public Employee(String id, String firstName, String lastName, String email, String phoneNumber, Gender gender, String location, List<String> jobPositions) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.location = location;
        this.jobPositions = jobPositions;
    }
}
