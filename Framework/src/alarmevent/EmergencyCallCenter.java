package alarmevent;

import eventbroker.EventPublisher;

public class EmergencyCallCenter extends EventPublisher {
    String name;
    public EmergencyCallCenter(String name){
        super();
        this.name=name;
    }
    public void incomingCall(AlarmEvent alarmEvent){publishEvent(alarmEvent);}
}
