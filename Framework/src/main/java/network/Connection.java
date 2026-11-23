package network;

import eventbroker.Event;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Connection {
    private final ObjectInputStream reader;
    private final ObjectOutputStream writer;
    private final Socket socket;
    private ReceiverThread receiverThread;

    public Connection(InetAddress host, int port) throws IOException {
        super();
        socket = new Socket(host, port);
        writer = new ObjectOutputStream(socket.getOutputStream());
        writer.flush();
        reader = new ObjectInputStream(socket.getInputStream());
    }

    public Connection(Socket socket) throws IOException {
        super();
        this.socket=socket;
        writer = new ObjectOutputStream(socket.getOutputStream());
        writer.flush();
        reader = new ObjectInputStream(socket.getInputStream());
    }

    public void send(Event e) throws IOException {
        System.out.println("Bezig met sturen..");
        writer.writeObject(e);
    }

    public void receive(Network network){
        System.out.println("Bezig met ontvangen..");
        receiverThread = new ReceiverThread(network,reader);
        receiverThread.start();
    }

    public void close(){
        try {
           receiverThread.terminate();
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

class ReceiverThread extends Thread{
    private final Network network;
    private final ObjectInputStream reader;
    private boolean runFlag = true;

    protected ReceiverThread(Network network,ObjectInputStream reader){
        this.network=network;
        this.reader=reader;
    }

    @Override
    public void run() {
        Event e = null;
        while (runFlag) {
            try {
                e = (Event) reader.readObject();
            } catch (IOException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }

            network.publishEvent(e);
        }
    }

    public void terminate(){
        runFlag=false;
    }
}
