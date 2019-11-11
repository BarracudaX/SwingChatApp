package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Server implements Runnable {
    private static final BlockingQueue<String> messageBuffer = new LinkedBlockingQueue<>();

    private final ServerSocket serverSocket;

    private final MessageConsumer consumer;

    private final ExecutorService consumerService = Executors.newSingleThreadExecutor();

    private final List<Thread> clientThreads = new ArrayList<>();

    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(2_000);
        consumer = new MessageConsumer(messageBuffer);
    }

    @Override
    public void run() {
        try {
            consumerService.submit(consumer);

            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Socket client = serverSocket.accept();//unblocked every two seconds because of setSoTimeout
                    consumer.addSocket(client);
                    Thread thread = new ClientThread(client, messageBuffer);
                    thread.start();
                    clientThreads.add(thread);
                } catch (SocketTimeoutException ignored) {
                    //ignored,thrown only to allow server to check whether thread was interrupted.
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }finally{
            Thread.interrupted();//clear it before close because awaitTermination in close when called with interrupted status throws exception.
            close();
            Thread.currentThread().interrupt();
        }
    }

    private void close() {
        if (serverSocket != null) {
            try {
                serverSocket.close();
            } catch (IOException ignored) {
            }
        }

        for (Thread clientThread : clientThreads) {
            clientThread.interrupt();
        }

        consumerService.shutdownNow();
        boolean consumerServiceHasTerminated = false;
        try {
            consumerServiceHasTerminated = consumerService.awaitTermination(5, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (!consumerServiceHasTerminated) {
                System.err.println("Couldn't stop consumer service from running.");
            }
        }
    }

}
