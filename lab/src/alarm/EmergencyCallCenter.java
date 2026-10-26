package alarm;

import java.util.HashSet;
import java.util.Set;

public class EmergencyCallCenter {
    
    private String emergencyNumber;
    private Set<AlarmListener> listeners = new HashSet<AlarmListener>();
    
    public EmergencyCallCenter(String number){
        this.emergencyNumber = number;
    }
    
    public void incomingCall(String alarm, String location){
        System.out.println("Incoming call on number "+emergencyNumber);
        for(AlarmListener listener : listeners){
            listener.alarm(new Alarm(alarm, location));
        }
    }
    
    public void addListener(AlarmListener listener){
        this.listeners.add(listener);
    }
    
    public void removeListener(AlarmListener listener){
        this.listeners.remove(listener);
    }
}
