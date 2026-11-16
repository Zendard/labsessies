package greeter;
import java.net.*;
import java.io.*;

public class Server {
	 public static void main(String[] args) {
	      DatagramSocket socket=null;
	      int serverPort=1234;
	      int bufferSize=100;
	      
	      try {
	    	  socket=new DatagramSocket(serverPort);
	    	  while(true) {
	    		  byte[] buffer=new byte[bufferSize];
	    		  DatagramPacket request=new DatagramPacket(buffer,buffer.length);
	    		  socket.receive(request);
	    		  String rMess="Hello, "+ (new String(request.getData()));
	    		  DatagramPacket reply =new DatagramPacket(rMess.getBytes(),rMess.length(),request.getAddress(),request.getPort());
	    		  socket.send(reply);
	    	  }
	      } catch(SocketException e) {
	    	  System.err.println(e);
	      } catch(IOException e) {
	    	  System.err.println(e);
	      } finally {
	    	  if(socket!=null) socket.close();
	      }
	   }

}
