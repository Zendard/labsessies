package alarmevent;

import eventbroker.Event;

import java.io.Serializable;

public class AlarmEvent extends Event implements Serializable {
    
    private final String location;
    
    public AlarmEvent(String type, String location){
        super(type, type+" at "+location);
        this.location = location;
    }
    
    public String getLocation(){
        return location;
    }
}
