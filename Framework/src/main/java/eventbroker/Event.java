package eventbroker;

import java.io.Serializable;

public class Event implements Serializable {

    protected String type;
    protected String message;
    private static long n = 0;
    private long id = n++;

    public Event(String type, String message) {
        this.type = type;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "ID" + id + "[" + type + "] : " + message;
    }
}
