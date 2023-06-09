package com.senla.course;

public class Main {

    public static void main(String[] args) {

        Employee developer =  new Employee().createEmployee("Developer", 7000);
        Employee manager = new Employee().createEmployee("Manager", 8000);
        Employee architect = new Employee().createEmployee("Architect", 9800);
        Employee qaTester = new Employee().createEmployee("QATester", 3500);
        Employee seller = new Employee().createEmployee("Seller", 5350);

        Company company = new Company();

        company.staff.add(developer);
        company.staff.add(manager);
        company.staff.add(architect);
        company.staff.add(qaTester);
        company.staff.add(seller);


        System.out.println("Total Monthly Salary: " + Company.totalMonthlySalary(company.staff));

    }

}




