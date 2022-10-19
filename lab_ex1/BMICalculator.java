package lab_ex1;

import java.util.Scanner;

public class BMICalculator {

    public static void main(String[] args) {
        BMICalculator bmiCalculator = new BMICalculator();
        Scanner sc = new Scanner(System.in);

        printIntroduction();

        double index = bmiCalculator.getBMI(sc);
        String status = getStatus(index);

        reportResult(121221084, index, status);
    }

    public static void printIntroduction() {
        System.out.println("Calculator for your body mass index(BMI)!");
    }

    public double getBMI(Scanner sc) {
        System.out.println("Enter your height in inches: ");
        double cm = Double.parseDouble(sc.next()) * 2.54;

        System.out.println("Enter your weight in pounds: ");
        double kg = Double.parseDouble(sc.next()) / 2.2046;

        return bmiFor(kg, cm);
    }

    private double bmiFor(double weight, double height) {
        return weight * 703 / (Math.pow(height, 2));
    }

    public static String getStatus(double index) {
        if (index <= 18.5) {
            return "underweight";
        } else if (index <= 25) {
            return "normal";
        } else if (index <= 30) {
            return "overweight";
        } else {
            return "obese";
        }
    }

    public static void reportResult(int num, double index, String status) {
        System.out.printf("Person with number: %d, has BMI = %.2f and his status is: %s%n", num, index, status);
    }
}
