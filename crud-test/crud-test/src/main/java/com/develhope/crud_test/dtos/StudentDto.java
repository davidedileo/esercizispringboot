package com.develhope.crud_test.dtos;

public class StudentDto {
    private Long id;

    private String name;

    private String surname;

    private boolean isWorking;

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

    public Boolean getIsWorking() {
        return isWorking;
    }

    public void setIsWorking(Boolean working) {
        isWorking = working;
    }
}
