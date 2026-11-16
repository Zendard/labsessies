package greeter;
import java.net.*;
import java.io.*;

public class Client {
	public static void main(String[] args) {
		InetAddress host = null;
		int serverPort = 1234;
		DatagramSocket socket = null;
		BufferedReader consoleInput = new BufferedReader(new InputStreamReader(
				System.in));
		String name = "";
		try {
			host = InetAddress.getLocalHost();
			socket = new DatagramSocket(1300);
			do {
				// read in a name
				System.out.println("Enter a name : ");
				consoleInput = new BufferedReader(new InputStreamReader(
						System.in));
				name = consoleInput.readLine();

				if (!(name.equals("stop"))) {
					// send the name
					byte[] messageBytes = name.getBytes();
					DatagramPacket request = new DatagramPacket(messageBytes,
							messageBytes.length, host, serverPort);
					socket.send(request);

					// receive reply
					byte[] buffer = new byte[50];
					DatagramPacket reply = new DatagramPacket(buffer,
							buffer.length);
					socket.receive(reply);

					// print the greeting
					System.out.println("Reply from server = "
							+ (new String(reply.getData())));
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
				socket.close();
		}
	}
}

