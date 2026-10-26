
package order;

import eventbroker.EventPublisher;


public class Customer extends EventPublisher implements Runnable{

    public void run(){
        for(int i=0;i<noOrders;i++){
            this.buy("item-"+i);
        }
    }

    private final String name;
    private final int noOrders;
    private final Thread thread;
    
    public Customer(String name,int noOrders){
        this.name = name;
        this.noOrders=noOrders;
        this.thread=new Thread(this);
        thread.start();
    }
    
    public void buy(String item){
        //System.out.println(name + " places an order for item "+item);
        publishEvent(new OrderEvent(name, item));
    }

    public void join() throws InterruptedException {
        this.thread.join();
    }
}
