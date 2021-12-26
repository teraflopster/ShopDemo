package com.example.demo.models;

import antlr.BaseAST;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Document(collection = "users")
public class User {
    @Id
    @Indexed(unique = true)
    @Field(value = "email")
    private String email;

    @Field(value = "first_name")
    private String firstName;

    @Field(value = "last_name")
    private String lastName;

    @Field(value = "password")
    private String password;

    @Field(value = "role")
    private Role role;

    @Field(value = "status")
    private Status status;

    public User(){}

    public User(String email, String firstName, String lastName, String password, Role role, Status status) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = passwordEncoder().encode(password);
        this.role = role;
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
