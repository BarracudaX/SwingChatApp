package com.lloseng.ocsf.server;
import com.model.*;

import java.time.LocalDateTime;

public class MyServer extends AbstractServer  {

    /**
     * Constructs a new server.
     *
     * @param port the port number on which to listen.
     */
    public MyServer(int port) {
        super(port);
    }

    @Override
    protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
        Message message = (Message) msg;

        message.setDate(LocalDateTime.now());

        sendToAllClients(message);
    }
}
