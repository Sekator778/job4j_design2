package ru.job4j.io.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TestWrite {
    public static void main(String[] args) throws IOException {

        try (ServerSocket server = new ServerSocket(9000)) {
            System.out.println("server started");
            while (true) {
                try (
                        Socket socket = server.accept();
                        BufferedWriter writer =
                                new BufferedWriter(
                                        new OutputStreamWriter(
                                                socket.getOutputStream()));
                        BufferedReader reader =
                                new BufferedReader(
                                        new InputStreamReader(
                                                socket.getInputStream()))
                ) {
                    String request = reader.readLine();
                    System.out.println("Request: " + request);
                    String response = "HELLO FROM SERVER: " + request.length();
                    System.out.println("Response: " + response);
                    writer.write("HTTP/1.1 200 OK\r\n\r\n");

                    writer.write(response);
                    writer.newLine();
                    writer.flush();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}