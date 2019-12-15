package com.project.server.controller;

import com.project.server.view.ServerGUI;

public class ViewController {

    private final ServerGUI serverGUI;

    public ViewController(ServerGUI serverGUI) {
        this.serverGUI = serverGUI;
    }

    public void switchToStartServer() {
        serverGUI.switchToStartServer();
    }

    public void switchToStatusServer() {
        serverGUI.switchToServerStatus();
    }

    public void showError(String error) {
        serverGUI.showError(error);
    }

    public void showMessage(String message) {
        serverGUI.showMessage(message);
    }


}
