package alarmevent;

import eventbroker.EventBroker;

public class Main {
    
    public static void main(String[] args){
        EventBroker eventBroker = EventBroker.getEventBroker();
        
        Hospital hospital1 = new Hospital("UZ");
        eventBroker.addEventListener("fire",hospital1);
        eventBroker.addEventListener("crash",hospital1);
        Hospital hospital2 = new Hospital("AZ");
        eventBroker.addEventListener("fire",hospital2);
        eventBroker.addEventListener("crash",hospital2);
        PoliceDepartment police = new PoliceDepartment();
        eventBroker.addEventListener(police);
        FireDepartment fireDepartment = new FireDepartment();
        eventBroker.addEventListener("fire",fireDepartment);

        EmergencyCallCenter callCenter = new EmergencyCallCenter("112");

        EmergencyCallCenter callCenterPolice = new EmergencyCallCenter("101");

        callCenter.incomingCall(new AlarmEvent("crash", "Plateaustraat"));
        callCenter.incomingCall(new AlarmEvent("assault", "VeldStraat"));
        callCenter.incomingCall(new AlarmEvent("fire", "Zwijnaardse Steenweg"));
        callCenterPolice.incomingCall(new AlarmEvent("robbery", "Zwijnaardse Steenweg"));

    }
}
