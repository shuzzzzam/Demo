package com.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Employee {
    @JsonProperty("employee_name")
    private String name;

    @JsonProperty("employee_age")
    private long age;

    @JsonProperty("employee_salary")
    private String salary;

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public long getAge() {
        return age;
    }
    public void setAge(long age) {
        this.age = age;
    }
}
