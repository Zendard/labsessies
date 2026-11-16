
package order;


import eventbroker.Event;
import eventbroker.EventBroker;
import eventbroker.EventListener;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class OrderProcessor implements EventListener {

    protected static int processedOrders = 0;
    final  ExecutorService threadPool;

    public static synchronized OrderProcessor make_OrderProcessor(int numThreads){
        return new OrderProcessor(numThreads);
    }
    OrderProcessor(int numThreads){
        this.threadPool = Executors.newFixedThreadPool(numThreads);
        EventBroker.getEventBroker().addEventListener(OrderEvent.TYPE_ORDER, this);
    }
    
    @Override
    public void handleEvent(Event e) {  
        OrderEvent order = (OrderEvent) e;
        threadPool.execute(new OrderProcessorTask(order));
        processedOrders++;
    }

    @Override
    public void terminate() {
        threadPool.shutdown();
    }

    public static int getNumberOfOrders(){
        return processedOrders;
    }
}

class OrderProcessorTask implements  Runnable{
    private final OrderEvent event;

    OrderProcessorTask(OrderEvent event) {
        this.event = event;
    }

    @Override
    public void run() {
       processOrder(event);
    }

    protected void processOrder(OrderEvent order){
        doWork(10);
        //System.out.println("Order of item "+order.getItem()+" for customer "+order.getCustomer()+ " processed!");
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