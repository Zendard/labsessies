package eventbroker;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

final public class EventBroker implements Runnable {

    protected HashMap<String,Set<EventListener>> listeners = new HashMap<String,Set<EventListener>>();
    protected final static EventBroker broker = new EventBroker();
    protected LinkedList<QueueItem> queue = new LinkedList<>();
    private EventBroker() {
        this.listeners.put("*",new HashSet<>());
    }

    private record QueueItem(Event event, EventPublisher source){}

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
        queue.add(new QueueItem(e,source));
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
    public void run(){
        while(!this.queue.isEmpty()){
            EventPublisher source = queue.poll().source();
            Event event = queue.poll().event();
            this.process(source, event);
        }
    }
}
