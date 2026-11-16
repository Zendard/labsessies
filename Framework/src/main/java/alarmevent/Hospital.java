package alarmevent;

import eventbroker.Event;
import eventbroker.EventBroker;
import eventbroker.EventListener;

public class Hospital implements EventListener {

    private String name;
    
    public Hospital(String name){
        this.name = name;
        EventBroker.getEventBroker().addEventListener("fire",this);
        EventBroker.getEventBroker().addEventListener("crash",this);
    }
    
    public String getName(){
        return name;
    }
    
    @Override
    public void handleEvent(Event e) {
        if(e instanceof AlarmEvent){
            AlarmEvent alarm = (AlarmEvent) e;
            System.out.println(name+" sends an ambulance to "+alarm.getLocation()+" for "+alarm.getType());
        }
    }
    
}
