package scholarship.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class StudentClient {
    private static final int SERVER_PORT = 7777;
    private static final String SERVER_HOST = "localhost";

    private final String name;
    private final double semesterGrade;
    private final double parentIncome;
    private final String facultyNumber;
    private final int kindOfScholarship;

    public StudentClient(String name, double semesterGrade, double parentIncome, String facultyNumber, int kindOfScholarship) {
        this.name = name;
        this.semesterGrade = semesterGrade;
        this.parentIncome = parentIncome;
        this.facultyNumber = facultyNumber;
        this.kindOfScholarship = kindOfScholarship;
    }

    public void sendRequest() {

        try (Socket socket = new Socket(SERVER_HOST, SERVER_PORT);
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)) {

            while (true) {
                System.out.println("Client is sending information...");

                writer.println(name + "," + semesterGrade + "," + parentIncome + "," + facultyNumber + "," + kindOfScholarship);
            }

        } catch (IOException e) {
            System.out.println("There was a problem with the client!");
        }
    }
}
