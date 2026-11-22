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
    public final List<Connection> connections=new ArrayList<>();

    public Network(int port) throws IOException {
        super();
        new ConnectionListener(this,port);
        EventBroker.getEventBroker().addEventListener(this);
    }

    public Network() throws IOException {
        super();
        EventBroker.getEventBroker().addEventListener(this);
    }
    /*
    public void connect(InetAddress host,int port){
        try {
            Socket socket = new Socket(host, port);
            Connection connection = new Connection(socket);
            connections.add(connection);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    */
     public void connect(InetAddress host, int port){
         try {
             try{
             Socket socket = new Socket(host, port);
             Connection connection = new Connection(socket);
             connections.add(connection);
             connection.receive(this);
             System.out.println("ik ben client");} catch (Exception e) {
                 throw new RuntimeException(e);
             }
         }catch(Exception e) {
                 System.out.println("ik word server!");

                 Thread wachtThread = new Thread(() -> {
                     try {
                         ServerSocket serverSocket = new ServerSocket(port);

                         while (!Thread.currentThread().isInterrupted()) {
                             try {
                                 Socket socket = serverSocket.accept();
                                 System.out.println("hier");
                                 Connection connection = new Connection(socket);
                                 connections.add(connection);
                                 connection.receive(this);

                                 System.out.println("Nieuwe connectie");
                             } catch (IOException ex) {
                                 System.out.println(ex);
                                 break;
                             }
                         }
                     }catch (IOException ex) {
                         throw new RuntimeException(ex);
                 }});

                 wachtThread.start();


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
