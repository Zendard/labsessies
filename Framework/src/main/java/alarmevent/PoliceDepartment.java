package alarmevent;

import eventbroker.Event;
import eventbroker.EventBroker;
import eventbroker.EventListener;
import java.util.Random;

public class PoliceDepartment implements EventListener {

    Random r = new Random();
    
    public PoliceDepartment(){
        // TODO is this ok?
        EventBroker.getEventBroker().addEventListener(this);
    }
    
    @Override
    public void handleEvent(Event e) {
        if(e instanceof AlarmEvent){
            AlarmEvent alarm = (AlarmEvent) e;
            System.out.println("Police unit "+r.nextInt(10)+" is checking out the "+alarm.getType()+" at "+alarm.getLocation());
        }
    }
    
}
