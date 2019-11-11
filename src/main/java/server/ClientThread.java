package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;

public class ClientThread extends Thread {

    private final BlockingQueue<String> messages;

    private BufferedReader reader;

    private final Socket socket;

    protected ClientThread(Socket socket, BlockingQueue<String> messages) {
        this.messages = messages;
        this.socket = socket;
    }

    @Override
    public void interrupt() {
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        super.interrupt();
    }

    @Override
    public void run() {

        try {
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            interrupt();
        }

        while (!isInterrupted()) {
            try {
                String newMessage = reader.readLine();
                messages.put(newMessage);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            } catch (Exception ignored) {
            }
        }
    }
}
