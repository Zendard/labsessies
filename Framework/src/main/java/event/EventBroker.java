package event;

import java.util.HashSet;
import java.util.Set;

final public class EventBroker {

    protected Set<EventListener> listeners = new HashSet<EventListener>();
    protected final static EventBroker broker = new EventBroker();

    private EventBroker() {
    }

    public static EventBroker getEventBroker() {
        return broker;
    }

    public void addEventListener(EventListener s) {
        listeners.add(s);
    }

    public void removeEventListener(EventListener s) {
        listeners.remove(s);
    }

    void addEvent(EventPublisher source, Event e) {
        process(source, e);
    }

    private void process(EventPublisher source, Event e) {
        for (EventListener l : listeners) {
            if (l != source) {
                l.handleEvent(e); // prevent loops !
            }
        }
    }
}
