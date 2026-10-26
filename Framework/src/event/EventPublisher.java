package event;

public class EventPublisher {

    public void publishEvent(Event e) {
        EventBroker.getEventBroker().addEvent(this, e);
    }
}
