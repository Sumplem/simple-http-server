package com.sumplem.httpserver;

import com.sumplem.httpserver.core.ServerListener;

public class HttpServer {
    public static void main(String[] args) {
        int port = 8080;
        ServerListener serverListener = new ServerListener(port);
        serverListener.start();
    }
}