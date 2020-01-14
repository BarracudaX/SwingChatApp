package server.controllers;

import server.MyServer;
import java.io.IOException;
import java.net.ServerSocket;

public class StartServerController {

    private final ServerViewController viewController;

    private final MyServer server;

    public StartServerController(ServerViewController viewController, MyServer server) {
        this.viewController = viewController;
        this.server = server;
    }

    public void startServer(int port) {
        if (!validPort(port)) {
            wrongPortNumber();
            return ;
        }

        if (!checkPortNumber(port)) {
            viewController.showError("The port number " + port + " is not available for use.");
            return ;
        }

        server.setPort(port);
        try {
            server.listen();
        } catch (IOException e) {
            e.printStackTrace();
        }
        viewController.switchToStatusServer(port);
    }

    private void wrongPortNumber() {
        viewController.showError("The port number must be between 1024 and 65354.");
    }

    public boolean validPort(int port) {
        return port >= 1024 && port <= 65535;
    }

    public void checkIfPortNumberOpen(int port) {
        if (checkPortNumber(port)) {
            viewController.showMessage("The port number " + port + " is open and can be used.");
        } else {
            viewController.showError("The port number " + port + " is not available for use.");
        }
    }

    private boolean checkPortNumber(int port) {
        if (!validPort(port)) {
            wrongPortNumber();
            return false;
        }

        ServerSocket ss = null;

        try {
            ss = new ServerSocket(port);
            ss.setReuseAddress(true);
            return true;
        } catch (IOException ignored) {

        } finally {

            if (ss != null) {
                try {
                    ss.close();
                } catch (IOException e) {
                    /* should not be thrown */
                }
            }
        }

        return false;
    }
}
