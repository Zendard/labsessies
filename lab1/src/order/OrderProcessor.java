
package order;


import eventbroker.Event;
import eventbroker.EventBroker;
import eventbroker.EventListener;


public class OrderProcessor implements EventListener {

    protected static int processedOrders = 0;
    
    public OrderProcessor(){
        EventBroker.getEventBroker().addEventListener(OrderEvent.TYPE_ORDER, this);
    }
    
    @Override
    public void handleEvent(Event e) {  
        OrderEvent order = (OrderEvent) e;
        processOrder(order);
    }

    
    protected synchronized void processOrder(OrderEvent order){
        processedOrders++;
        this.doWork(50);
        //System.out.println("Order of item "+order.getItem()+" for customer "+order.getCustomer()+ " processed!");
    }

    public synchronized static int getNumberOfOrders(){
        return processedOrders;
    }
   
    // do some dummy work for @milliseconds ms
    private void doWork(int milliseconds){
        long t1 = System.currentTimeMillis();
        int counter = 0;
        while(System.currentTimeMillis()-t1 < milliseconds){
            counter++;
        }
    }
}
