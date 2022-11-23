package com.krzysztofzerman.jsonbodies;

public class Person {
    int id;
    String firstName;
    String lastName;
    String email;
    int companyId;

    public Person(int id, String firstName, String lastName, String email, int companyId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.companyId = companyId;
    }
}