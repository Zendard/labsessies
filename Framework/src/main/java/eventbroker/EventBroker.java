package eventbroker;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

final public class EventBroker implements Runnable {

    private final HashMap<String,Set<EventListener>> listeners = new HashMap<>();
    private final Set<EventListener> listenersList = new HashSet<>();
    private final Thread thread= new Thread(this);
    private final static EventBroker broker = new EventBroker();
    private final LinkedList<QueueItem> queue = new LinkedList<>();
    private EventBroker() {
        this.listeners.put("*",new HashSet<>());
    }
    private final Object queueLock=new Object();
    private boolean moetStoppen;

    private record QueueItem(Event event, EventPublisher source){}

    public static EventBroker getEventBroker() {
        return broker;
    }

    public void addEventListener(EventListener s){
       listeners.get("*").add(s);
       listenersList.add(s);
    }

    public void addEventListener(String type, EventListener s) {
        listenersList.add(s);
        if (listeners.containsKey(type)) {
            listeners.get(type).add(s);
        }else{
            var set = new HashSet<EventListener>();
            set.add(s);
            listeners.put(type,set);
        }
    }

    public void removeEventListener(EventListener s) {
        listenersList.remove(s);
        for (Set<EventListener> i:listeners.values()) {
            i.remove(s);
        }
    }

    public void removeEventListener(String type, EventListener s){
        listeners.get(type).remove(s);
    }

    void addEvent(EventPublisher source, Event e) {
        synchronized (queueLock) {
            queue.add(new QueueItem(e, source));
            queueLock.notifyAll();          // maak hem wakker
        }
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
    /*
    public void run(){
        QueueItem temp = null;
        while(!(temp==null) || queueFlag){
            synchronized (queueLock) {
                while (!queueHasData && !queueFlag) {
                    try {
                        queueLock.wait();
                    } catch (InterruptedException _){}
                }
                temp = this.pollQueue();
                if (!(temp == null)) {
                    EventPublisher source = temp.source();
                    Event event = temp.event();
                    this.process(source, event);
                }else {
                    queueHasData=false;
                }
            }
        }
    }
    */
    public void run() {
        while (true) {
            QueueItem temp;
            synchronized (queueLock) {
                while (queue.isEmpty() && !moetStoppen) {
                    try {
                        queueLock.wait();   // queue leeg
                    } catch (InterruptedException _) {}
                }
                if (queue.isEmpty()) {
                    return;                 // stop gevraagd en niets meer te doen
                }
                temp = queue.poll();
            }
            // buiten de lock: producers kunnen intussen events toevoegen
            process(temp.source(), temp.event());
        }
    }

    public void start(){
        moetStoppen=false;
        thread.start();
    }

    public void stop() throws InterruptedException {
        synchronized (queueLock) {
            moetStoppen = true;
            queueLock.notifyAll();
        }
        thread.join();                      // eerst resterende events verwerken
        for (EventListener l : listenersList) {
            l.shutdown();                   // daarna pas de listeners afsluiten
        }
    }
}
