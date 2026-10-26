package alarmwhiteboard;

import alarm.Alarm;
import alarm.AlarmListener;

import java.util.Set;

public class EmergencyCallCenter {

    public EmergencyCallCenter(String name){}

    public void incomingCall(Alarm alarm) {
        Set<AlarmListener> listeners= Whiteboard.getWhiteboard().getAlarmListeners(alarm.getType());
        for (AlarmListener listener:listeners){
            listener.alarm(alarm);
        }
    }
}
