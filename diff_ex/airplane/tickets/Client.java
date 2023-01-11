package airplane.tickets;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Client cl = new Client();
        cl.client();
    }

    public void client() {
        try(Socket client = new Socket("localhost", 8080);
        Scanner console = new Scanner(System.in);Scanner in = new Scanner(client.getInputStream());
        PrintStream out = new PrintStream(client.getOutputStream())) {

            System.out.println("Connected to the server!\n");
            System.out.println(in.nextLine());
            out.println(console.nextLine());

            String destination = in.nextLine();
            if (destination.contains("Sorry")) {
                System.out.println(destination);
                return;
            } else {
                System.out.println(destination);
            }

            buyTicket(console, in , out);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void buyTicket(Scanner console, Scanner in, PrintStream out) {
        System.out.println(in.nextLine());
        out.println(console.nextLine());

        // Identity
        System.out.println(in.nextLine());
        out.println(console.nextLine());

        String reply = in.nextLine();
        System.out.println(reply);
        if (reply.contains("first")) {
            System.out.println(in.nextLine());
        }

        System.out.println(in.nextLine());

        while (true) {
            out.println(console.nextLine());
            String reply1 = in.nextLine();
            if (reply1.contains("Thank")) {
                System.out.println(reply1);
                break;
            } else {
                System.out.println(reply1);
            }
        }
    }
}
