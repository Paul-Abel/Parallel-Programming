package exercise1;

import java.util.*;

class IceCreamShop {
    private final int numSeats = 12;
    private int numCustomers = 0;
    private final Thread[] service = new Thread[4];
    private final List<String> seats = new ArrayList<>(numSeats);
    private static final Queue<String> waitingOutside = new LinkedList<>();
    private static final Queue<String> waitingOrder = new LinkedList<>();
    private final Random random = new Random();


    public IceCreamShop() {
        //When exercise1.IceCreamShop is initialized, the service get defined, so every Shop has his own service
        service[0] = new Thread(new Service("Alex", this));
        service[0].start();
        service[1] = new Thread(new Service("Rabia", this));
        service[1].start();
        service[2] = new Thread(new Service("Denis", this));
        service[2].start();
        service[3] = new Thread(new Service("Ana", this));
        service[3].start();
    }

    public synchronized void enterShop(String customerName) throws InterruptedException {
        while (seats.size() >= numSeats) {
            System.out.println(customerName + " is waiting outside.");
            waitingOutside.add(customerName);
            wait();
        }

        seats.add(customerName);
        numCustomers++;
        System.out.println(customerName + " has entered the shop and occupied a seat. Total customers: " + numCustomers);
    }

    public synchronized void leaveShop(String customerName) throws InterruptedException {
        seats.remove(customerName);
        numCustomers--;
        System.out.println(customerName + " has finished eating and left the shop. Total customers: " + numCustomers);
        if (!waitingOutside.isEmpty()) {
            notify();
        }

    }

    public void placeOrder(String name) throws InterruptedException {
        synchronized (service) {
            waitingOrder.add(name);
            System.out.println(name + " has add an Order.");
            service.notify();
        }
    }

    public void serveOrder(String name) throws InterruptedException {
        synchronized (service) {
            while (waitingOrder.isEmpty()) {
                service.wait();
            }
            String customer = waitingOrder.poll();
            System.out.println(customer + " has get order from " + name + ".");
        }
        int waiting = random.nextInt(3001) + 5000;
        //System.out.println(name + " is waiting " + waiting);
        Thread.sleep(waiting);


    }


}