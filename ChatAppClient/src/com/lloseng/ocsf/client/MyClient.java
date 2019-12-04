package com.lloseng.ocsf.client;

import com.project.Message;

import javax.swing.*;

public class MyClient extends AbstractClient {

    private JTextArea chat;

    /**
     * Constructs the client.
     *
     * @param host the server's host name.
     * @param port the port number.
     */
    public MyClient(String host, int port,JTextArea chat) {
        super(host, port);
        this.chat = chat;
    }

    public void setChat(JTextArea chat) {
        this.chat = chat;
    }

    @Override
    protected void handleMessageFromServer(Object msg) {
        Message message = (Message) msg;
        SwingUtilities.invokeLater(() -> {
            chat.append(message.getDate()+" "+message.getUser().getUsername()+" : "+message.getBody()+"\n\n");
        });
    }

}
