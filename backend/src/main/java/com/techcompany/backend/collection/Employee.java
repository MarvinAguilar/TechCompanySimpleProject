package com.techcompany.backend.collection;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "employees")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Employee {
    @Id
    @Field("_id")
    private String id;
    private String firstName;
    private String lastName;
    private String about;
    @Indexed(unique = true)
    private String email;
    private String phoneNumber;
    private Gender gender;
    private String location;
    private String jobPosition;

    public Employee(String id, String firstName, String lastName, String about, String email, String phoneNumber, Gender gender, String location, String jobPosition) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.about = about;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.location = location;
        this.jobPosition = jobPosition;
    }
}
