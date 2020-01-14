package iee.ihu.swingchatapplication.client.controllers;

import iee.ihu.swingchatapplication.client.MyClient;
import iee.ihu.swingchatapplication.model.Message;
import iee.ihu.swingchatapplication.client.views.ChatView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChatController {

    private final ClientViewController viewController;

    private ChatView chatView;

    private MyClient client;

    private final ConnectController connectController;

    private final List<String> messages = new ArrayList<>();

    public ChatController(ClientViewController viewController, ConnectController connectController, MyClient client) {
        if (!client.isConnected()) {
            throw new IllegalArgumentException("The client is not connected!");
        }
        this.viewController = viewController;
        this.connectController = connectController;
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

    public boolean hasNewMessages() {
        messages.addAll(client.getMessages());
        return !messages.isEmpty();
    }

    public String getNextMessage() {
        messages.addAll(client.getMessages());

        if (!messages.isEmpty()) {
            return messages.remove(0);
        }

        return "";
    }

    public String getUsername() {
        return connectController.getUser().getUsername();
    }
}
