package serialization;

import java.io.*;
import java.net.*;

public class Client {

    public static void main(String[] args) throws Exception {
        InetAddress host = null;
        int serverPort = 1234;
        BufferedReader consoleInput;
        String firstName;
        Socket socket;
            host = InetAddress.getLocalHost();
            socket = new Socket(host,serverPort);
            ObjectOutputStream writer= new ObjectOutputStream(socket.getOutputStream());
            writer.flush();
            ObjectInputStream reader = new ObjectInputStream(socket.getInputStream());
            do {
                // read in first name
                System.out.println("Enter first name : ");
                consoleInput = new BufferedReader(new InputStreamReader(
                        System.in));
                firstName = consoleInput.readLine();
                if (firstName.equals("stop")){break;}

                // read in last name
                System.out.println("Enter last name : ");
                consoleInput = new BufferedReader(new InputStreamReader(
                        System.in));
                String lastName = consoleInput.readLine();

                Person person = new Person(firstName, lastName);
                // send the name
                writer.writeObject(person);

                // receive reply
                Person newPerson = (Person) reader.readObject();

                // print the greeting
                System.out.println("Reply from server = " + newPerson.toString());
            } while (!(firstName.equals("stop")));
            socket.close();
        }
    }
