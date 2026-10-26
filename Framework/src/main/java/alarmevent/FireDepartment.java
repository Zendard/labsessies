package alarmevent;

import eventbroker.Event;
import eventbroker.EventListener;

import java.util.Random;

public class FireDepartment implements EventListener {

    Random random = new Random();

    public FireDepartment(){
    }

    @Override
    public void handleEvent(Event e) {
        System.out.println("Firefighting unit "+random.nextInt(10)+" is checking out the "+e.getType()+": "+e.getMessage());
    }
}
