package ru.client_server.clients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientOne {
    public static void main(String[] args) {
        try (Socket clientSocket = new Socket("localhost", 8989)) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream());

            String serverRequestFirst = in.readLine();
            System.out.println(serverRequestFirst);
            System.out.println("Write name of city :");
            String fromClient = reader.readLine();
            out.write(fromClient + "\n");
            out.flush();
            String serverRequest = in.readLine();
            System.out.println(serverRequest);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.println("You has finished your turn.");

    }
}
