
package alarmevent;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import eventbroker.EventBroker;
import network.Network;


public class Server {

    public static void main(String[] args) throws IOException{
        new Network(5000);

        Hospital UZ = new Hospital("UZ");
        Hospital AZ = new Hospital("AZ");
        PoliceDepartment police = new PoliceDepartment();
        FireDepartment fireDepartment = new FireDepartment();


        EventBroker.getEventBroker().start();
    }
}
