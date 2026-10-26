package eventbroker;

import alarm.EmergencyCallCenter;
import alarm.FireDepartment;
import alarm.Hospital;
import alarm.PoliceDepartment;

public class Main {
    
    public static void main(String[] args){
        
        Hospital hospital1 = new Hospital("UZ");
        Hospital hospital2 = new Hospital("AZ");
        PoliceDepartment police = new PoliceDepartment();
        FireDepartment fireDepartment = new FireDepartment();

        EmergencyCallCenter callCenter = new EmergencyCallCenter("112");
        callCenter.addListener(hospital1);
        callCenter.addListener(hospital2);
        callCenter.addListener(police);
        callCenter.addListener(fireDepartment);

        EmergencyCallCenter callCenterPolice = new EmergencyCallCenter("101");
        callCenterPolice.addListener(police);

        callCenter.incomingCall("crash", "Plateaustraat");
        callCenter.incomingCall("assault", "Veldstraat");
        callCenter.incomingCall("fire", "Zwijnaardse Steenweg");
        callCenterPolice.incomingCall("robbery", "Zwijnaardse Steenweg");

    }
}
