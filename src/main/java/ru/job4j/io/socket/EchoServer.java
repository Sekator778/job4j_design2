package ru.job4j.io.socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Сначала запишем стартовую строку с кодом состояния,
 * затем добавим пустую строку (\r\n\r\n),
 * и только после этого записываем наше сообщение.
 *  example
 *  out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
 *  далее уже что там по логике out.write(response.getBytes());
 */
public class EchoServer {

    private static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String response;
                    String request = in.readLine();
                    if (request.contains("?msg=Exit")) {
                        response = "Goodbye!";
                        server.close();
                    } else if (request.contains("?msg=Hello")) {
                        response = "Hello, friend!";
                    } else {
                        response = "What";
                    }
                    System.out.println(request);
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    out.write(response.getBytes());
                    out.flush();
                }
            }
        } catch (IOException e) {
            LOG.error("IO error: ", e);
        }
    }
}