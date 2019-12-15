package com.project.client.controller;

import com.lloseng.ocsf.client.AbstractClient;
import com.project.Message;
import com.project.User;
import com.project.client.view.ChatGUI;

import java.io.IOException;

public class ChatController {

    private final ViewController viewController;

    private ChatGUI chatGUI;

    private AbstractClient client;

    private final ConnectController connectController;

    public ChatController(ViewController viewController,ConnectController connectController) {
        this.viewController = viewController;
        this.connectController = connectController;
    }

    public void setChatGUI(ChatGUI chatGUI) {
        this.chatGUI = chatGUI;
    }

    public void setClient(AbstractClient client) {
        this.client = client;
    }

    public void disconnect() {
        try {
            client.closeConnection();
            viewController.requestForConnectionView();
        } catch (IOException e) {
            viewController.showError(e.getMessage());
        }
    }

    public void sendMessage(String message) {
        Message messageObject = new Message(message, connectController.getUser());
        try {
            client.sendToServer(messageObject);
        } catch (IOException e) {
            viewController.showError(e.getMessage());
        }
    }

    public void messageReceived(String message) {
        chatGUI.appendMessage(message);
    }
}
