package Alyoshka;

import java.net.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Server {
    public static final Integer PORT = 8080;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            String startTime = DateTimeFormatter.ofPattern("HH:mm:ss (dd-MM-yyyy): ").format(LocalDateTime.now());
            System.out.println(startTime + "Server was launched");
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
                ) {
                    String connectionStartTime = DateTimeFormatter.ofPattern("HH:mm:ss (dd-MM-yyyy): ").format(LocalDateTime.now());
                    System.out.println(connectionStartTime + "New connection accepted");
                    out.println("Hello! What is your name?");
                    String name = in.readLine();

                    while (true) {
                        out.println("Are you child? (yes/no)");
                        String answer = in.readLine();
                        switch (answer) {
                            case "yes": {
                                out.println(String.format("Welcome to the kids area, %s! Let's play!", name));
                                break;
                            }
                            case "no": {
                                out.println(String.format("Dear %s, we should make sure about your age. Please, write it below:", name));
                                int ageAnswer = Integer.parseInt(in.readLine());
                                if (ageAnswer < 18) {
                                    out.println(String.format("Tiny %s, you are a child, so welcome to the kids area and let's play!", name));
                                } else {
                                    out.println(String.format("Welcome to the adult zone ;), %s! Have a good rest or a good working day!", name));
                                }
                                break;
                            }
                            default: {
                                out.println(String.format("Just give the answer for this question, %s, please!", name));
                                continue;
                            }
                        }
                        String connectionFinishTime = DateTimeFormatter.ofPattern("HH:mm:ss (dd-MM-yyyy): ").format(LocalDateTime.now());
                        System.out.printf(connectionFinishTime + "User with nickname \"%s\" left us. Connection was closed.\n", name);
                        break;
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
