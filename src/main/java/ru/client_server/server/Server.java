package ru.client_server.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8989)) {
            System.out.println("Starting the server!");
            boolean isFirst = true;
            String city = "";
            String fromClient;
            while (true) {
                try (Socket socket = serverSocket.accept();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()));
                     PrintWriter out = new PrintWriter(socket.getOutputStream(), false)
                ) {
                    if (isFirst) {
                        out.write("???" + "\n");
                        out.flush();
                        isFirst = false;
                        city = in.readLine();
                        out.write("OK");
                        out.flush();
                        continue;
                    } else {
                        out.write("Last name of city is - " + city + "\n");
                        out.flush();
                    }
                    fromClient = in.readLine();
                    System.out.println("Answer is : " + fromClient);
                    String fromClientToLowerCase = fromClient.toLowerCase();
                    String cityToLowerCase = city.toLowerCase();
                    int indexStart = city.length() - 1;
                    int indexEnd = indexStart + 1;
                    String test = cityToLowerCase.substring(indexStart, indexEnd);
                    if (fromClientToLowerCase.startsWith(test)) {
                        city = fromClient;
                        out.write("OK");
                        out.flush();
                    } else {
                        out.write("NOT OK");
                        out.flush();
                    }
                }
            }
        } catch (IOException ex) {
            System.out.println("Не могу стартовать сервер");
            ex.printStackTrace();
        }

    }
}
