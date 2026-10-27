package eventbroker;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

final public class EventBroker implements Runnable {

    private final HashMap<String,Set<EventListener>> listeners = new HashMap<String,Set<EventListener>>();
    private final Thread thread= new Thread(this);
    private final static EventBroker broker = new EventBroker();
    private final LinkedList<QueueItem> queue = new LinkedList<>();
    private EventBroker() {
        this.listeners.put("*",new HashSet<>());
    }
    private boolean queueFlag=true;

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
            var set = new HashSet<EventListener>();
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
        this.addToQueue(new QueueItem(e,source));
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
        QueueItem temp = null;
        while(!(temp==null) ||queueFlag){
            temp=this.pollQueue();
            if (!(temp==null)) {
                EventPublisher source = temp.source();
                Event event = temp.event();
                this.process(source, event);
            }
        }
    }

    private synchronized void addToQueue(QueueItem item){
        this.queue.add(item);
    }

    private synchronized QueueItem pollQueue(){
        return this.queue.poll();
    }

    public void start(){
        queueFlag=true;
        thread.start();
    }

    public void stop() throws InterruptedException {
       queueFlag=false;
       thread.join();
    }
}
