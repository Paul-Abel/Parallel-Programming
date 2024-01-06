package exercise2;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class IceCreamShop {
    private final int numSeats = 12;
    private int numCustomers = 0;
    private final List<String> seats = new ArrayList<>(numSeats);
    private static final Queue<String> waitingOutside = new LinkedList<>();
    private static final Queue<String> waitingOrder = new LinkedList<>();
    private final Random random = new Random();
    private final Lock lock = new ReentrantLock();
    private final Condition seatsAvailable = lock.newCondition();
    private final Condition orderAvailable = lock.newCondition();

    //Initialize Servce for the Shop (Constructor)
    public IceCreamShop() {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        executorService.execute(() -> new Service("Alex", this).run());
        executorService.execute(() -> new Service("Rabia", this).run());
        executorService.execute(() -> new Service("Denis", this).run());
        executorService.execute(() -> new Service("Ana", this).run());

    }

    //Customer wants to enter shop
    public void enterShop(String customerName) throws InterruptedException {
        lock.lock();
        try {
            while (seats.size() >= numSeats) {
                System.out.println(customerName + " is waiting outside.");
                waitingOutside.add(customerName);
                seatsAvailable.await();
            }

            seats.add(customerName);
            numCustomers++;
            System.out.println(customerName + " has entered the shop and occupied a seat. Total customers: " + numCustomers);
        } finally {
            lock.unlock();
        }
    }

    //Customer leave Shop
    public void leaveShop(String customerName) throws InterruptedException {
        lock.lock();
        try {
            seats.remove(customerName);
            numCustomers--;
            System.out.println(customerName + " has finished eating and left the shop. Total customers: " + numCustomers);
            if (!waitingOutside.isEmpty()) {
                seatsAvailable.signal();
            }
        } finally {
            lock.unlock();
        }
    }

    //Customer place a order
    public void placeOrder(String name) throws InterruptedException {
        lock.lock();
        try {
            waitingOrder.add(name);
            System.out.println(name + " has added an order.");
            orderAvailable.signal();
        } finally {
            lock.unlock();
        }
    }

    //Servce try to serve order
    public void serveOrder(String name) throws InterruptedException {
        lock.lock();
        try {
            while (waitingOrder.isEmpty()) {
                orderAvailable.await();
            }
            String customer = waitingOrder.poll();
            System.out.println(customer + " has received an order from " + name + ".");
        } finally {
            lock.unlock();
        }
        int waiting = random.nextInt(3001) + 5000;
        Thread.sleep(waiting);
    }
}