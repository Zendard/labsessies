package network;

import eventbroker.Event;
import eventbroker.EventBroker;
import eventbroker.EventListener;
import eventbroker.EventPublisher;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Network extends EventPublisher implements EventListener {
    public final List<Connection> connections;

    public Network(int port) throws IOException {
        super();
        this.connections=new ArrayList<>();
        new ConnectionListener(this,port);
        EventBroker.getEventBroker().addEventListener(this);
    }

    public Network() throws IOException {
        super();
        this.connections=new ArrayList<>();
        EventBroker.getEventBroker().addEventListener(this);
    }

    public void connect(InetAddress host,int port){
        try {
            Socket socket = new Socket(host, port);
            Connection connection = new Connection(socket);
            connections.add(connection);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void handleEvent(Event e) {
        try {
            for(Connection connection:connections) {
                connection.send(e);
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void terminate() {
        for(Connection connection:connections) {
            connection.close();
        }
    }
}
