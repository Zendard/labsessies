package alarmevent;

import eventbroker.EventPublisher;

public class EmergencyCallCenter extends EventPublisher{
    
    private String emergencyNumber;
    
    public EmergencyCallCenter(String number){
        this.emergencyNumber = number;
    }
    
    public void incomingCall(String alarm, String location){
        System.out.println("Incoming call on number "+emergencyNumber);
        publishEvent(new AlarmEvent(alarm, location));
    }
    
}
