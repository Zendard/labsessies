
package main;

import main.orderhandeler;
import order.Customer;
import order.OrderProcessor;

import java.util.HashSet;
import java.util.Set;


public class Main {
    
    public static int noOrders = 1_000;

    public static void main(String[] args){
        
        String[] names = new String[]{"Jan", "Piet", "Joris", "Corneel"};
        
        OrderProcessor orderProcessor = new OrderProcessor();  
        Set<Thread> threadSet = new HashSet<>();
        for(String name : names){
            Customer customer = new Customer(name);
            Thread t = new Thread(new orderhandeler(customer,noOrders));
            threadSet.add(t);
            t.start();


        }
        for(Thread t: threadSet){
            try{
                t.join();
            }
            catch(InterruptedException e){
                return;
            }
        }
        System.out.println(OrderProcessor.getNumberOfOrders());

    }
    
}
