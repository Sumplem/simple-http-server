package com.http_server;

import com.http_server.core.ServerListener;

public class HttpServer {
    public static void main(String[] args) {
        int port = 8080;
        ServerListener serverListener = new ServerListener(port);
        serverListener.start();
    }
}