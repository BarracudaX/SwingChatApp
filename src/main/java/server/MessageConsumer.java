package server;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class MessageConsumer implements Runnable {

    private final BlockingQueue<String> messages;

    private final ConcurrentMap<String, Socket> sockets = new ConcurrentHashMap<>();

    public MessageConsumer(BlockingQueue<String> messages) {
        this.messages = messages;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                String message = messages.take();
                sendMessage(message);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    public final void addSocket(Socket socket) throws IOException {
        String key = socket.getInetAddress().getHostAddress()+socket.getPort();
        sockets.putIfAbsent(key,socket);
    }

    private final void sendMessage(String message) {
        for (Map.Entry<String,Socket> entry : sockets.entrySet()) {
            if (Thread.currentThread().isInterrupted()) {
                break;
            }
            try {
                new PrintWriter(entry.getValue().getOutputStream(),true).println(message);
            } catch (IOException e) {
                sockets.remove(entry.getKey());
            }
        }
    }

}
