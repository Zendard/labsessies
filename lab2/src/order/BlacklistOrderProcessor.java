
package order;

import eventbroker.EventBroker;
import java.util.HashSet;
import java.util.Set;


public class BlacklistOrderProcessor extends OrderProcessor {

    private Set<String> blacklist;
    
    public BlacklistOrderProcessor(){
        super();
        
        // some work to get to the blacklist data
        try {
            Thread.sleep(10);
        } catch (InterruptedException ex) {}
       
        // add the blacklisted customers
        blacklist = new HashSet<String>();
        blacklist.add("Jan");
    }
    
    @Override
    protected void processOrder(OrderEvent order){
        // ignore blacklisted customers
        if(blacklist.contains(order.getCustomer())){
            System.out.println("Order of customer "+order.getCustomer()+" discarded");
            return;
        }
        
        super.processOrder(order);
    }
    
}
