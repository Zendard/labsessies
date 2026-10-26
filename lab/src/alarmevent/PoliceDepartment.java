package alarmevent;

import eventbroker.Event;
import eventbroker.EventListener;

import java.util.Random;

public class PoliceDepartment implements EventListener {

    Random random = new Random();

    public PoliceDepartment(){
    }

    @Override
    public void handleEvent(Event e) {
        System.out.println("Police unit "+random.nextInt(10)+" is checking out the "+e.getType()+": "+e.getMessage());
    }
}
