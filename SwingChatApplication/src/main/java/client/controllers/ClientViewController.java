package client.controllers;

import apps.Client;

public class ClientViewController {

    private final Client view;

    public ClientViewController(Client view) {
        this.view = view;
    }

    public void showError(String error) {
        view.showError(error);
    }

    public void requestForChatView() {
        view.switchToChatView();
    }

    public void requestForConnectionView() {
        view.switchToConnectView();
    }

}
