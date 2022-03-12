package com.example.CSW_Practice.entity;

public class Employee {
    private int id;
    private String name;
    private int age;
    private int status;

    public Employee(int id, String name, int age, int status) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
