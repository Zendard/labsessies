package alarmevent;

import eventbroker.Event;
import eventbroker.EventBroker;
import eventbroker.EventListener;

public class FireDepartment implements EventListener {

    public FireDepartment(){
        EventBroker.getEventBroker().addEventListener("fire",this);
    }
    
    @Override
    public void handleEvent(Event e) {
        if(e instanceof AlarmEvent){
            AlarmEvent alarm = (AlarmEvent) e;
            System.out.println("Fire squad on the move to "+alarm.getLocation()+" for "+alarm.getType());
        }
    }
    
}
