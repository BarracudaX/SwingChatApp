package com.project.client.controller;

import com.lloseng.ocsf.client.AbstractClient;
import com.project.client.view.ClientGUI;

public class ViewController {

    private final ClientGUI clientGUI;

    private AbstractClient client;

    public ViewController(ClientGUI clientGUI) {
        this.clientGUI = clientGUI;
    }

    public void showError(String error) {
        clientGUI.showError(error);
    }

    public void requestForChatView() {
        clientGUI.switchToChatGUI();
    }

    public void requestForConnectionView() {

        clientGUI.switchToConnectView();
    }

    public void setClient(AbstractClient client) {
        this.client = client;
    }
}
