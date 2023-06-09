package com.senla.course;

import com.github.javafaker.Faker;

import java.util.Random;

public class Employee extends Person {
    private static final int MIN_AGE = 20;
    private static final int MAX_AGE = 65;
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

    public Employee() {
        super();
    }

    public Employee(String firstName, String lastName, int age, String position, int salary) {
        super(firstName, lastName, age);
        this.position = position;
        this.salary = salary;
    }

    public Employee createEmployee(String position, int salary) {
        Random random = new Random();
        Faker faker = new Faker();

        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        int age = random.nextInt(MAX_AGE-MIN_AGE) + MIN_AGE;
        return new Employee(firstName, lastName, age, position, salary);
    }

}
