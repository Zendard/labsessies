package alarmwhiteboard;

import alarm.AlarmListener;
import alarm.Hospital;

import java.util.*;

public class Whiteboard {
    private final static Whiteboard whiteboard = new Whiteboard();
    private Map<String, Set<AlarmListener>> listeners = new HashMap<>();
    private List<Hospital> hospitals= new ArrayList<>();
    private int hospitalsIndex=0;

    private Whiteboard(){
        this.listeners.put("*", new HashSet<>());
    }

    public static Whiteboard getWhiteboard(){
        return whiteboard;
    }

    public void addEventListener(AlarmListener s){
        listeners.get("*").add(s);
        if (isHospital(s) && !hospitals.contains(s)){
            hospitals.add((Hospital) s);
        }
    }

    public void addEventListener(String type, AlarmListener s) {
        if (listeners.containsKey(type)) {
            listeners.get(type).add(s);
        }else{
            Set<AlarmListener> set = new HashSet<>();
            set.add(s);
            listeners.put(type,set);
        }

        if (isHospital(s) && !hospitals.contains(s)){
            hospitals.add((Hospital) s);
        }
    }

    public void removeEventListener(EventListener s) {
        for (Set<AlarmListener> i:listeners.values()) {
            i.remove(s);
        }
    }

    public void removeEventListener(String type, EventListener s){
        listeners.get(type).remove(s);
    }

    public Set<AlarmListener> getAlarmListeners(String type){
        Set<AlarmListener> set = listeners.getOrDefault(type,new HashSet<>());
        set.addAll(listeners.get("*"));

        if (set.stream().anyMatch(this::isHospital)) {
            set.removeIf(this::isHospital);

            set.add((AlarmListener) hospitals.get(hospitalsIndex));
            hospitalsIndex = (hospitalsIndex + 1) % hospitals.toArray().length;
        }

        return set;
    }

    private Boolean isHospital(AlarmListener listener){
        return listener.getClass()== Hospital.class;
    }
}
