package eventbroker;

public interface EventListener {

    void handleEvent(Event e);
    void terminate();
}
