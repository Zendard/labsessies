package main;

import order.Customer;
import order.OrderProcessor;

public class orderhandeler implements Runnable{
    private final Customer customer;
    private final int orders;
    public orderhandeler(Customer customer, int orders){
        this.customer=customer;
        this.orders=orders;
    }
    public void run(){

        for(int i=0;i<orders;i++){
            this.customer.buy("item-"+i);
        }
    }
}
