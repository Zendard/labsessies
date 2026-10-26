
package order;

import eventbroker.Event;


public class OrderEvent extends Event {

    public final static String TYPE_ORDER = "ORDER";
    
    private final String item;
    private final String customer;
    
    public OrderEvent(String customer, String item){
        super(TYPE_ORDER, customer +" orders "+item);
        this.item = item;
        this.customer = customer;
    }
    
    public String getCustomer(){
        return customer;
    }
    
    public String getItem(){
        return item;
    }
}
