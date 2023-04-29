package ru.job4j.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            /* 9000 - порт. По умолчанию адрес будет localhost. */
            while (!server.isClosed()) {
            /* метод accept() заставляет программу ждать подключений по указанному порту,
             работа программы продолжится только после подключения клиента.
             После успешного подключения метод возвращает объект Socket, который используется для взаимодействия с клиентом. */
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    for (String str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine()) {
                        System.out.println(str);
                        if (Objects.equals(str, "GET /?msg=Bye HTTP/1.1")) {
                            server.close();
                            return;
                        }
                    }
                    out.flush();
                }
            }
        }
    }
}