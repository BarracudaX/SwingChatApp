package client.controllers;

import client.MyClient;
import model.User;

import java.io.IOException;

public class ConnectController {

    private User user;

    private final MyClient client;

    private final ClientViewController viewController;

    public ConnectController(ClientViewController viewController, MyClient client) {
        if (client.isConnected()) {
            throw new IllegalArgumentException("The client is still connected!");
        }
        this.client = client;
        this.viewController = viewController;
    }

    public User getUser(){
        return user;
    }

    public void connect(int portValue, String hostValue, String usernameValue) {
        if (portValue < 1024 || portValue > 65_535) {
            viewController.showError("The port number must be between 1024 and 65.353");
            return;
        }

        if (usernameValue.isEmpty()) {
            viewController.showError("Please fill the field the username.");
            return ;
        }

        user = new User(usernameValue);

        client.setPort(portValue);

        client.setHost(hostValue);

        try {
            client.openConnection();
            viewController.requestForChatView();
        } catch (IOException e) {
            viewController.showError("Failed to connect to the server.\nException message : \n" + e.getMessage());
        }
    }

    public void wrongHost() {
        viewController.showError(
                "The host field is invalid.Please fill it with a valid IPV4 address.\n"+
                "Valid IPV4 address has the following pattern: XXX.XXX.XXX.XXX , where X is digit.\n"+
                "Example of valid IPV4 address : 192.168.5.5");
    }
}
