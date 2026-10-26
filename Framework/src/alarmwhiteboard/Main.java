package alarmwhiteboard;

import alarm.Alarm;
import alarmwhiteboard.EmergencyCallCenter;
import alarm.FireDepartment;
import alarm.Hospital;
import alarm.PoliceDepartment;

public class Main {
    
    public static void main(String[] args){
        Whiteboard whiteboard = Whiteboard.getWhiteboard();
        
        Hospital hospital1 = new Hospital("UZ");
        Hospital hospital2 = new Hospital("AZ");
        PoliceDepartment police = new PoliceDepartment();
        FireDepartment fireDepartment = new FireDepartment();

        EmergencyCallCenter callCenter = new EmergencyCallCenter("112");
        whiteboard.addEventListener("crash",hospital1);
        whiteboard.addEventListener("fire",hospital1);
        whiteboard.addEventListener("crash",hospital2);
        whiteboard.addEventListener("fire",hospital2);
        whiteboard.addEventListener(police);
        whiteboard.addEventListener("fire",fireDepartment);

        EmergencyCallCenter callCenterPolice = new EmergencyCallCenter("101");

        callCenter.incomingCall(new Alarm("crash", "Plateaustraat"));
        callCenter.incomingCall(new Alarm("assault", "Veldstraat"));
        callCenter.incomingCall(new Alarm("fire", "Zwijnaardse Steenweg"));
        callCenterPolice.incomingCall(new Alarm("robbery", "Zwijnaardse Steenweg"));

    }
}
