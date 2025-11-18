package com.http_server.core;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerListener extends Thread {
    private int port;
    private ServerSocket serverSocket;

    public ServerListener(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(port);

            while (serverSocket.isBound() && !serverSocket.isClosed()) {
                HttpWorker worker = new HttpWorker(serverSocket.accept());
                worker.start();
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
