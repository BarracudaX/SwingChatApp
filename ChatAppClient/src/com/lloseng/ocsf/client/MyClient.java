package com.lloseng.ocsf.client;

import com.project.Message;
import com.project.client.controller.ChatController;

import javax.swing.*;

public class MyClient extends AbstractClient {

    private final ChatController controller;

    /**
     * Constructs the client.
     *
     * @param host the server's host name.
     * @param port the port number.
     */
    public MyClient(String host,int port,ChatController controller) {
        super(host, port);
        this.controller = controller;
    }

    @Override
    protected void handleMessageFromServer(Object msg) {
        Message message = (Message) msg;
        SwingUtilities.invokeLater(() -> {
            controller.messageReceived(message.getDate() + " " + message.getUser().getUsername() + " : " + message.getBody() + "\n\n");
        });
    }

}
