package alarmevent;

import java.io.*;
import java.net.InetAddress;

import eventbroker.EventBroker;
import network.Network;

public class Client_101 {

    public static void main(String[] args) throws IOException {
        // client code
        Network netwerk = new Network();
        netwerk.connect(InetAddress.getLocalHost(), 5000);
        EventBroker.getEventBroker().start();

        EmergencyCallCenter callCenter101 = new EmergencyCallCenter("101");

        callCenter101.incomingCall("crash","Plateaustraat (client101)");

        try{
            Thread.sleep(10000);} catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Laatste bericht versturen");
        callCenter101.incomingCall("assault","Veldstraat (client101)");


    }
}
