package com.senla.course;

public class Employee extends Person {

    String position;
    int salary;

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Employee(String firstName, String lastName, int age, String position, int salary) {
        super(firstName, lastName, age);
        this.position = position;
        this.salary = salary;
    }
}
