
package main;

import order.Customer;
import order.OrderProcessor;


public class Main {
    
    public static int noOrders = 5;    

    public static void main(String[] args){
        
        String[] names = new String[]{"Jan", "Piet", "Joris", "Corneel"};
        
        OrderProcessor orderProcessor = new OrderProcessor();  

        for(String name : names){
            Customer customer = new Customer(name);
            for(int i=0;i<noOrders;i++){
                customer.buy("item-"+i);
            }
        }
    }
    
}
