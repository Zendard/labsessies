package greeter;
import java.net.*;
import java.io.*;

public class Client {
	public static void main(String[] args) {
		InetAddress host = null;
		int serverPort = 1024;
		int bufferSize = 100;
		BufferedReader consoleInput = new BufferedReader(new InputStreamReader(
				System.in));
		String name = "";
		Socket socket = null;
        try {
			host = InetAddress.getLocalHost();
			socket = new Socket(host,serverPort);
			PrintWriter writer= new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()),bufferSize);
			do {
				// read in a name
				System.out.println("Enter a name : ");
				consoleInput = new BufferedReader(new InputStreamReader(
						System.in));
				name = consoleInput.readLine();

				if (!(name.equals("stop"))) {
					// send the name
					writer.println(name);

					// receive reply
					String reply = reader.readLine();

					// print the greeting
					System.out.println("Reply from server = " + reply);
				}
			} while (!(name.equals("stop")));
		} catch (UnknownHostException e) {
			System.err.println(e);
		} catch (SocketException e) {
			System.err.println(e);
		} catch (IOException e) {
			System.err.println(e);
		} finally {
			if (socket != null)
				try {
					socket.close();
				}catch(Exception _){}
		}
	}
}

