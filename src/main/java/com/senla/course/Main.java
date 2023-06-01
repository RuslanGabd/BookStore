package com.senla.course;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        final Random random = new Random();
        int quantity = 3;
        int number;

        while (quantity > 0) {
            number = random.nextInt(900) + 100;
            System.out.println("Three-digit number: " + number);
            System.out.println("Sum first digits = " + Sum(number));
            System.out.println("");
            quantity--;
        }
    }

    public static int Sum(int n) {
        int digit;
        int sum = 0;
        while (n > 0) {
            digit = n % 10;
            sum = sum + digit;
            n = n / 10;
        }
        return sum;
    }
}
