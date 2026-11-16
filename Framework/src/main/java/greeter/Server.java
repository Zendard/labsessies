package greeter;
import java.net.*;
import java.io.*;

public class Server {
	 public static void main(String[] args) {
	      ServerSocket serverSocket=null;
	      int serverPort=1024;
	      int bufferSize=100;
	      
	      try {
	    	  serverSocket=new ServerSocket(serverPort);
	    	  while(true) {
				  Socket socket = serverSocket.accept();
				  System.out.println("Accepted client");
				  PrintWriter writer= new PrintWriter(new OutputStreamWriter( socket.getOutputStream()),true);
				  BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()),bufferSize);
                  String result="";
                  while (result !="stop") {
					  result = reader.readLine();
					  String rMess = "Hello, " + result;
					  writer.println(rMess);
				  }
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

}
