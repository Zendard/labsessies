package alarmevent;

import eventbroker.Event;

public class AlarmEvent extends Event {
    protected String location;

    public AlarmEvent(String type, String location){
        super(type,"ALARM! "+type+" at "+location);
    }
}
