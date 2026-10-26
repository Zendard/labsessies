package alarm;

public class Alarm {
    
    private final String location;
    private final String type;
    
    public Alarm(String type, String location){
        this.location = location;
        this.type = type;
    }
    
    public String getLocation(){
        return location;
    }
    
    public String getType(){
        return type;
    }
}
