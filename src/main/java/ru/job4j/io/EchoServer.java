package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

    private static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());

    private static String search(String str, ServerSocket serverSocket) throws IOException {
        String rsl = null;
        if (str.contains("msg=Exit")) {
            rsl = " Exit";
            serverSocket.close();
        } else if (str.contains("Hello")) {
            rsl = "Hello";
        } else {
            rsl = "What";
        }
        return rsl;
    }

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String answer = null;
                    answer = "HTTP/1.1 200 OK\r\n\r\n";
                    for (String str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine()) {
                        System.out.println(str);
                        if (str.startsWith("GET")) {
                            answer = search(str, server);
                        }
                    }
                    out.write("HTTP/1.1 200 ok\r\n\r\n".getBytes());
                    out.write(answer.getBytes());
                    out.flush();
                }
            }


        } catch (IOException ioException) {
            LOG.error("IOException", ioException);
        }
    }
}