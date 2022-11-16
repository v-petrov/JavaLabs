package lab_ex.lab_ex5;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StudentInfoScanner {

    private static final Pattern NAME_PATTERN = Pattern.compile("^[A-Z][a-z]+$");
    private static final Pattern FACULTY_NUMBER_PATTERN = Pattern.compile("^[1-9][0-9]*$");

    public static String scanStudentInfo(Scanner scanner) {
        StringBuilder result = new StringBuilder("Hello, my name is: ");

        String name;
        String facultyNumber;
        Matcher nameMatcher;
        Matcher facultyNumberMatcher;
        int f_name = 0;
        int f_facultyNumber = 0;

        do {
            if (f_name == 1) {
                System.out.println("Your name doesn't meet the requirement (first letter upper case, followed by 1 or more lower case). Try again!");
            }

            name = scanner.nextLine();
            nameMatcher = NAME_PATTERN.matcher(name);
            f_name = 1;

        } while (!nameMatcher.matches());

        result.append(name).append(". My faculty number is: ");

        do {
            if (f_facultyNumber == 1) {
                System.out.println("Your faculty number doesn't meet the requirement (9 digits, first other than 0). Try again!");
            }

            facultyNumber = scanner.nextLine();
            facultyNumberMatcher = FACULTY_NUMBER_PATTERN.matcher(facultyNumber);
            f_facultyNumber = 1;

        } while (!facultyNumberMatcher.matches());

        result.append(facultyNumber).append(".");

        return result.toString();
    }

}
