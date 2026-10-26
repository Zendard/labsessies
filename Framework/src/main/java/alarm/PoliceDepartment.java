package alarm;

import java.util.Random;

public class PoliceDepartment implements AlarmListener {

    Random random = new Random();
    
    @Override
    public void alarm(Alarm alarm) {
        System.out.println("Police unit "+random.nextInt(10)+" is checking out the "+alarm.getType()+" at "+alarm.getLocation());
    }
    
}
