package com.senla.course;

import java.util.ArrayList;

public class Company {
    String nameCompany;
    String addressCompany;


    ArrayList<Employee> staff = new ArrayList<>();

    public String getNameCompany() {
        return nameCompany;
    }

    public void setNameCompany(String nameCompany) {
        this.nameCompany = nameCompany;
    }

    public String getAddressCompany() {
        return addressCompany;
    }

    public void setAddressCompany(String addressCompany) {
        this.addressCompany = addressCompany;
    }

    public ArrayList<Employee> getStaff() {
        return staff;
    }

    public void setStaff(ArrayList<Employee> staff) {
        this.staff = staff;
    }

    static int totalMonthlySalary(ArrayList<Employee> arrayList) {
        int i = 0;
        int sum = 0;
        while (i < arrayList.size()) {
            sum = sum + arrayList.get(i).getSalary();
            i++;
        }
        return sum;
    }
}
