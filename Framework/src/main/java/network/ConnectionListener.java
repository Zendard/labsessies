package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

// listens for incoming connections
public class ConnectionListener extends Thread{
    private final Network network;
    private boolean flag=true;
    private final ServerSocket serverSocket;

    public ConnectionListener(Network network, int serverPort) throws IOException {
        super();
        this.network=network;
        this.serverSocket=new ServerSocket(serverPort);
    }

    @Override
    public void run() {
        while(flag) {
            try {
                Socket socket = serverSocket.accept();
                network.connect(socket);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void terminate(){
        flag=false;
        network.terminate();
    }
}
