package com.project.server.controller;

import com.project.server.view.ServerView;

public class ViewController {

    private final ServerView view;

    public ViewController(ServerView view) {
        this.view = view;
    }

    public void switchToStartServer() {
        view.switchToStartServer();
    }

    public void switchToStatusServer(int port) {
        view.switchToServerStatus(port);
    }

    public void showError(String error) {
        view.showError(error);
    }

    public void showMessage(String message) {
        view.showMessage(message);
    }


}
