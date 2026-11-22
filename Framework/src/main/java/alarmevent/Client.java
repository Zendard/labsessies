package alarmevent;

import java.io.*;
import java.net.InetAddress;

import eventbroker.EventBroker;
import network.Network;

public class Client {

    public static void main(String[] args) throws IOException {
        // client code
        Network netwerk = new Network();
        netwerk.connect(InetAddress.getLocalHost(), 5000);
        EventBroker.getEventBroker().start();
        EmergencyCallCenter callCenter112 = new EmergencyCallCenter("122");
        EmergencyCallCenter callCenter101 = new EmergencyCallCenter("101");
        /*try{
        Thread.sleep(5000);} catch (InterruptedException e) {
            throw new RuntimeException(e);
        }*/
        callCenter101.incomingCall("crash","Plateaustraat");
        callCenter101.incomingCall("assault","Veldstraat");

        callCenter112.incomingCall("fire","Zwijnaardse Steenweg");
        callCenter112.incomingCall("assault","Overpoortstraat");
    }
}
