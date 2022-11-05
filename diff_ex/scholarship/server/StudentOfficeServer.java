package scholarship.server;

import scholarship.Scholarship;
import scholarship.Secretary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.net.*;

public class StudentOfficeServer {

    private static final int SERVER_PORT = 7777;

    private static final int NAME_OF_STUDENT = 0;
    private static final int GRADE_OF_STUDENT = 1;
    private static final int PARENT_INCOME = 2;
    private static final int FACULTY_NUMBER_OF_STUDENT = 3;
    private static final int KIND_OF_SCHOLARSHIP = 4;

    private static final int NUMBER_OF_SECRETARY = 2;
    private final ExecutorService executor = Executors.newFixedThreadPool(NUMBER_OF_SECRETARY);
    private final Queue<Scholarship> scholarshipQueue = new PriorityQueue<>();

    public void manager() {

        try (ServerSocket serverSocket = new ServerSocket(SERVER_PORT)) {

            System.out.println("Server has started!");

            Socket client;

            while (true) {

                client = serverSocket.accept();
                BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                Scholarship newScholarship = scholarship(reader);

                scholarshipQueue.add(newScholarship);
                sendToSecretary(scholarshipQueue.peek());
                reader.close();
            }
        } catch (IOException e) {
            executor.shutdownNow();
            throw new RuntimeException("There was a problem with the creation of the server!");
        }
    }

    private void sendToSecretary(Scholarship scholarship) {
        Secretary secretary = new Secretary(scholarship);
        executor.submit(secretary);
        scholarshipQueue.remove();
    }

    private Scholarship scholarship(BufferedReader reader) throws IOException {
        String line = reader.readLine();
        String[] tokens = line.split(",");
        String name = tokens[NAME_OF_STUDENT];
        double grade = Double.parseDouble(tokens[GRADE_OF_STUDENT]);
        double parentIncome = Double.parseDouble(tokens[PARENT_INCOME]);
        String facultyNumber = tokens[FACULTY_NUMBER_OF_STUDENT];
        int kind = Integer.parseInt(tokens[KIND_OF_SCHOLARSHIP]);

        return new Scholarship(name, grade, parentIncome, facultyNumber, kind);
    }
}
