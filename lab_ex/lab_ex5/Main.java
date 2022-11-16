package lab_ex.lab_ex5;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        String str = StudentInfoScanner.scanStudentInfo(new Scanner(System.in));
        System.out.println(str);
    }
}
