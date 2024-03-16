package org.example.entities;

import java.util.UUID;

public class Person {

    private UUID id;
    private String name;
    private String email;
    private String birthDate;

    public Person(){};

    public Person(String name, String email, String birthDate){
        this.id = UUID.randomUUID();
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
    }

    public UUID getId() {
        return id;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
}
