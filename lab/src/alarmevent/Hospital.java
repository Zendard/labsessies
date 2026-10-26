package alarmevent;

import eventbroker.Event;
import eventbroker.EventListener;

import java.util.Random;

public class Hospital implements EventListener {

    Random random = new Random();
    String name;

    public Hospital(String name){
        this.name=name;
    }

    @Override
    public void handleEvent(Event e) {
        System.out.println("Ambulance "+random.nextInt(10)+" is checking out the "+e.getType()+": "+e.getMessage());
    }
}
