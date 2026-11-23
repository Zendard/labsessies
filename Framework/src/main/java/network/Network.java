package network;

import eventbroker.Event;
import eventbroker.EventBroker;
import eventbroker.EventListener;
import eventbroker.EventPublisher;

import java.io.IOException;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Network extends EventPublisher implements EventListener {
    private final List<Connection> connections=new ArrayList<>();
    private ConnectionListener connectionListener = null;

    // Server
    public Network(int port) throws IOException {
        super();
        connectionListener= new ConnectionListener(this,port);
        EventBroker.getEventBroker().addEventListener(this);
        connectionListener.start();
    }

    // Client
    public Network() throws IOException {
        super();
        EventBroker.getEventBroker().addEventListener(this);
    }

    public void connect(InetAddress host,int port){
        try {
            Socket socket = new Socket(host, port);
            Connection connection = new Connection(socket);
            connection.receive(this);
            connections.add(connection);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void connect (Socket socket){
        try {
            Connection connection = new Connection(socket);
            connection.receive(this);
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
        if (connectionListener != null){
            connectionListener.terminate();
        }
        for(Connection connection:connections) {
            connection.close();
        }
    }
}
