package server.controllers;

import apps.ServerView;


public class ServerViewController {

    private final ServerView view;

    public ServerViewController(ServerView view) {
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
