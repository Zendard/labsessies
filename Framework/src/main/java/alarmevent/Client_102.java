package alarmevent;

import java.io.*;
import java.net.InetAddress;

import eventbroker.EventBroker;
import network.Network;

public class Client_102 {

    public static void main(String[] args) throws IOException {
        // client code
        Network netwerk = new Network();
        netwerk.connect(InetAddress.getLocalHost(), 5000);
        EventBroker.getEventBroker().start();
        EmergencyCallCenter callCenter112 = new EmergencyCallCenter("122");




        callCenter112.incomingCall("fire","Zwijnaardse Steenweg (client102)");

        try{
            Thread.sleep(5000);} catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        callCenter112.incomingCall("firel","Overpoortstraat (client102)");
    }
}
