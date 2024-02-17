package Alyoshka;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (Socket clientSocket = new Socket("netology.homework", Server.PORT);
             PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             Scanner sc = new Scanner(System.in)
        ) {
            //Greetings and Introduce yourself
            System.out.println(reader.readLine());
            writer.println(sc.nextLine());

            //question about tour age
            while (true) {
                System.out.println(reader.readLine());
                String answer = sc.nextLine();
                if (answer.equals("yes")) {
                    writer.println(answer);
                    System.out.println(reader.readLine());
                    break;
                } else if (answer.equals("no")) {
                    writer.println(answer);
                    //Make sure about your age
                    System.out.println(reader.readLine());
                    writer.println(sc.nextLine());
                    //Bye-bye
                    System.out.println(reader.readLine());
                    break;
                } else {
                    writer.println(answer);
                    System.out.println(reader.readLine());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
