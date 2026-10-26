package alarm;

public class Hospital implements AlarmListener {

    private String name;
    
    public Hospital(String name){
        this.name = name;
    }
    
    public String getName(){
        return name;
    }
    
    @Override
    public void alarm(Alarm alarm) {
        System.out.println(name+" sends an ambulance to "+alarm.getLocation()+" for "+alarm.getType());
    }
    
}
