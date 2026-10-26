
package main;

import eventbroker.EventBroker;
import order.Customer;
import order.OrderProcessor;

import java.util.ArrayList;
import java.util.List;


public class Main {
    
    public static int noOrders = 100;

    public static void main(String[] args){
        
        String[] names = new String[]{"Jan", "Piet", "Joris", "Corneel"};
        ArrayList<Customer> customers=new ArrayList<Customer>();
        
        OrderProcessor orderProcessor = new OrderProcessor();
        EventBroker.getEventBroker().start();

        for(String name : names){
            Customer customer = new Customer(name,noOrders);
            customers.add(customer);
        }

        customers.forEach((customer -> {
            try {
                customer.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }));

        try {
            EventBroker.getEventBroker().stop();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(OrderProcessor.getNumberOfOrders());
    }
    
}
