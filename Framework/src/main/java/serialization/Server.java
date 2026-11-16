package serialization;

import java.io.*;
import java.util.*;
import java.net.*;

public class Server {

    private static ArrayList<Person> db = new ArrayList<Person>();
    private static final int port=1234;

    public static void main(String[] args) throws Exception{
        fillInPhoneNumberDataBase();
        // server code
        ServerSocket serverSocket = new ServerSocket(port);
        while(true){
        Socket socket = serverSocket.accept();
        ObjectOutputStream writer=new ObjectOutputStream(socket.getOutputStream());
        writer.flush();
        ObjectInputStream reader=new ObjectInputStream(socket.getInputStream());

        while(true) {
            Person receivedPerson = (Person) reader.readObject();
            if (receivedPerson.getFirstName().equals("stop")){break;}
            Person newPerson = lookUpPhoneNumber(receivedPerson);

            writer.writeObject(newPerson);
        }
        serverSocket.close();

    }}

    private static void fillInPhoneNumberDataBase() {
        Person[] p = {new Person("Jan", "Janssens", new PhoneNumber("32", "9", "44 55 66")),
            new Person("Piet", "Pieters", new PhoneNumber("32", "50", "11 22 33")),
            new Person("Giovanni", "Totti", new PhoneNumber("49", "22", "00 99 88")),
            new Person("Jean", "Lefevre", new PhoneNumber("33", "4", "12 34 56"))};
        for (Person i : p) {
            db.add(i);
        }
    }

    private static Person lookUpPhoneNumber(Person p) {
        int index = db.indexOf(p);
        if (index >= 0) {
            return db.get(index);
        } else {
            p.setPhoneNumber(new PhoneNumber());
            return p;
        }
    }
}
