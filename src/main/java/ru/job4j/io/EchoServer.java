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
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {

        try (ServerSocket server = new ServerSocket(9000)) {
            /* 9000 - порт. По умолчанию адрес будет localhost.
            * http://localhost:9000/?msg=Hello набрать в браузере*/
            while (!server.isClosed()) {
            /* метод accept() заставляет программу ждать подключений по указанному порту,
             работа программы продолжится только после подключения клиента.
             После успешного подключения метод возвращает объект Socket, который используется для взаимодействия с клиентом. */
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());

                    String str = in.readLine();
                    System.out.println(str);
                    if (str.contains("?msg=Exit")) {
                        server.close();
                        return;
                    }
                    if (str.contains("?msg=Hello")) {
                        out.write("Hello.".getBytes());
                    } else {
                        out.write("What.".getBytes());
                    }
                    out.flush();
                    throw new IOException("Code for Exception test.");
                }
            }
        } catch (IOException e) {
            LOG.error("Exception in log example", e);
        }
    }
}