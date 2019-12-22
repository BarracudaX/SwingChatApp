package com.project.server.controller;

import com.lloseng.ocsf.server.AbstractServer;
import com.lloseng.ocsf.server.MyServer;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class ServerStatusController {

    private final ViewController viewController;
    private final MyServer server;

    public ServerStatusController(ViewController viewController, MyServer server) throws UnknownHostException {
        this.viewController = viewController;
        this.server = server;
    }

    public void stopServer() {
        try {
            server.close();
        } catch (IOException e) {
            viewController.showError(e.getMessage());
        }
        viewController.switchToStartServer();
    }

    public int getPort() {
        return server.getPort();
    }

    public String getIp() {
        try (final DatagramSocket socket = new DatagramSocket()) {
            socket.connect(InetAddress.getByName("8.8.8.8"), 10002);

            return socket.getLocalAddress().getHostAddress();
        } catch (UnknownHostException | SocketException e) {
            viewController.showError("Exception.Couldn't get ip.Exception message = " + e.getMessage());
        }
        return "Unknown";
    }

    public int getNumberOfConnectedUsers() {
        return server.getNumberOfClients();
    }

}
