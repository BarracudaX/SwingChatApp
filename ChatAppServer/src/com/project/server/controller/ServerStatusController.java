package com.project.server.controller;

import com.lloseng.ocsf.server.AbstractServer;

import java.io.IOException;

public class ServerStatusController {

    private final ViewController viewController;
    private final AbstractServer server;

    public ServerStatusController(ViewController viewController, AbstractServer server) {
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

}
