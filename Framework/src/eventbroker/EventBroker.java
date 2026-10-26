package eventbroker;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

final public class EventBroker {

    protected HashMap<String,Set<EventListener>> listeners = new HashMap<String,Set<EventListener>>();
    protected final static EventBroker broker = new EventBroker();

    private EventBroker() {
        this.listeners.put("*",new HashSet<>());
    }

    public static EventBroker getEventBroker() {
        return broker;
    }

    public void addEventListener(EventListener s){
       listeners.get("*").add(s);
    }

    public void addEventListener(String type, EventListener s) {
        if (listeners.containsKey(type)) {
            listeners.get(type).add(s);
        }else{
            HashSet set = new HashSet();
            set.add(s);
            listeners.put(type,set);
        }
    }

    public void removeEventListener(EventListener s) {
        for (Set<EventListener> i:listeners.values()) {
            i.remove(s);
        }
    }

    public void removeEventListener(String type, EventListener s){
        listeners.get(type).remove(s);
    }

    void addEvent(EventPublisher source, Event e) {
        process(source, e);
    }

    private void process(EventPublisher source, Event e) {
        for (EventListener l : listeners.getOrDefault(e.type,new HashSet<>())) {
            if (l != source) {
                l.handleEvent(e); // prevent loops !
            }
        }
        for (EventListener l: listeners.get("*")){
            if (l != source) {
                l.handleEvent(e); // prevent loops !
            }
        }
    }
}
