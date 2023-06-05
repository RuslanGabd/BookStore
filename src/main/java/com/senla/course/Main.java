package com.senla.course;

import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        final String nameCompany = "BigSoft";

        ArrayList<Employee> staff = new ArrayList<>();
        Employee developer = createEmployee("Developer", 7000);
        Employee manager = createEmployee("Manager", 8000);
        Employee architect = createEmployee("Architect", 9800);
        Employee qaTester = createEmployee("QATester", 3500);
        Employee seller = createEmployee("Seller", 5350);
        staff.add(developer);
        staff.add(manager);
        staff.add(architect);
        staff.add(qaTester);
        staff.add(seller);

        System.out.println("Total Monthly Salary: " + totalMonthlySalary(staff));

    }

    private static Employee createEmployee(String position, int salary) {
        Random random = new Random();
        Faker faker = new Faker();

        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        int age = random.nextInt(40) + 20;
        return new Employee(firstName, lastName, age, position, salary);
    }

    private static int totalMonthlySalary(ArrayList<Employee> arrayList) {
        int i = 0;
        int sum = 0;
        while (i < arrayList.size()) {
            sum = sum + arrayList.get(i).getSalary();
            i++;
        }
        return sum;
    }
}



