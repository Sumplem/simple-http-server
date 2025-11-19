package com.sumplem.httpserver.core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.time.LocalDateTime;

public class HttpConectionWorker extends Thread {
    private Socket socket;

    public HttpConectionWorker(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {

            System.out.println("Connected!");
            while (in.ready()) {
                System.out.println(in.readLine());
            }

            String html = """
                    <html>
                        <head>
                            <title>Http Server</title>
                        </head>
                        <body>
                            <h1> This is a http server</h1>
                        </body>
                    </html>
                        """;

            out.write("HTTP/1.1 200 OK\r\n");
            out.write("Server: Custom\r\n");
            out.write("Date:" + LocalDateTime.now() + "\r\n");
            out.write("Content-Length: " + html.length());
            out.write("Content-Type: text/html\r\n");
            out.write("\r\n");
            out.write(html);

        } catch (IOException e) {
            // TODO: handle exception
        } finally {
            try {
                socket.getInputStream().close();
                socket.getOutputStream().close();
                socket.close();
            } catch (IOException e) {
                // // TODO Auto-generated catch block
                // e.printStackTrace();
            }
        }
    }
}
