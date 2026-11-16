package parallelgreeter;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Server {

    public static void main(String[] args) {
        ServerSocket serverSocket=null;
        int serverPort=1024;
        int threadPoolSize=5;

        try {
            serverSocket=new ServerSocket(serverPort);
            ExecutorService threadPool = Executors.newFixedThreadPool(threadPoolSize);
            while(true) {
                Socket socket = serverSocket.accept();
                ClientHandler handler = new ClientHandler(socket);
                threadPool.execute(handler);
            }
        } catch(SocketException e) {
            System.err.println(e);
        } catch(IOException e) {
            System.err.println(e);
        } finally {
            if(serverSocket!=null) try{ serverSocket.close();}
            catch(Exception e){}
        }
    }

    private static class ClientHandler implements Runnable{
        Socket socket;

        public ClientHandler(Socket socket){
           this.socket=socket;
        }

        @Override
        public void run() {
            try {
                System.out.println("Accepted client");
                PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String result = "";
                while (result != "stop") {
                    result = reader.readLine();
                    String rMess = "Hello, " + result;
                    writer.println(rMess);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }


}

