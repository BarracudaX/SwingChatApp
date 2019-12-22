package com.project.client.controller;

import com.project.client.view.ClientView;

public class ViewController {

    private final ClientView view;

    public ViewController(ClientView view) {
        this.view = view;
    }

    public void showError(String error) {
        view.showError(error);
    }

    public void requestForChatView() {
        view.switchToChatGUI();
    }

    public void requestForConnectionView() {
        view.switchToConnectView();
    }

}
