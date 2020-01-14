package server.controllers;


import server.MyServer;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class ServerStatusController {

    private final ServerViewController viewController;
    private final MyServer server;

    public ServerStatusController(ServerViewController viewController, MyServer server) throws UnknownHostException {
        this.viewController = viewController;
        this.server = server;
    }

    public void stopServer() {
        try {
            server.close();
        } catch (IOException e) {
            viewController.showError(e.getMessage());
        }
        viewController.switchToStartServer();
    }

    public int getPort() {
        return server.getPort();
    }

    public String getIp() {
        try (final DatagramSocket socket = new DatagramSocket()) {
            socket.connect(InetAddress.getByName("8.8.8.8"), 10002);

            return socket.getLocalAddress().getHostAddress();
        } catch (UnknownHostException | SocketException e) {
            viewController.showError("Exception.Couldn't get ip.Exception message = " + e.getMessage());
        }
        return "Unknown";
    }

    public int getNumberOfConnectedUsers() {
        return server.getNumberOfClients();
    }

}
