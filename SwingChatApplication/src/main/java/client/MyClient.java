package client;



import framework.client.AbstractClient;
import model.Message;

import java.util.ArrayList;
import java.util.List;

public class MyClient extends AbstractClient {

    private final List<String> messages = new ArrayList<>();

    /**
     * Constructs the client.
     *
     * @param host the server's host name.
     * @param port the port number.
     */
    public MyClient(String host,int port) {
        super(host, port);
    }

    @Override
    protected void handleMessageFromServer(Object msg) {
        Message message = (Message) msg;
        messages.add(message.getDate() + " " + message.getUser().getUsername() + " : " + message.getBody() + "\n\n");
     }

    public List<String> getMessages(){
        List<String> messages = new ArrayList<>(this.messages);
        this.messages.clear();
        return messages;
    }

}
