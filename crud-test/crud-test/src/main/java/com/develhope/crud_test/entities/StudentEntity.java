package com.develhope.crud_test.entities;

import jakarta.persistence.*;

@Entity
@Table
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String surname;

    private boolean isWorking;

    public StudentEntity(Long id, String name, String surname, Boolean isWorking) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.isWorking = isWorking;
    }

    public StudentEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Boolean getisWorking() {
        return isWorking;
    }

    public void setIsWorking(Boolean working) {
        isWorking = working;
    }
}
