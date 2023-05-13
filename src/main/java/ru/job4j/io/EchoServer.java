package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            boolean finish = false;
            while (!server.isClosed() && !finish) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String str = in.readLine();
                    if (str.contains("?msg=Exit")) {
                        finish = true;
                        out.write("Server stopped".getBytes());
                    } else if (str.contains("?msg=Hello")) {
                        out.write("Hello".getBytes());
                    } else if (str.contains("?msg=What")) {
                        out.write("What".getBytes());
                    }
                    System.out.println(str);
                    out.flush();
                }
            }
        }
    }
}