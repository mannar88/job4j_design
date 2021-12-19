package ru.job4j.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                boolean socketAcses = true;
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String answer = null;
                    answer = "HTTP/1.1 200 OK\r\n\r\n";
                    for (String str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine()) {
                        System.out.println(str);
                        if (str.contains("Bye")) {
                            answer = "HTTP/1.1 200 close\r\n\r\n";
                            socketAcses = false;
                        }
                    }
                    out.write(answer.getBytes());
                    out.flush();
                }
                if (!socketAcses) {
                    server.close();
                }
            }


        }
    }
}