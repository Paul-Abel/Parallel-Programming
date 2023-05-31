import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class IceCreamShop {
    private final int numSeats = 12;
    private List<String> seats = new ArrayList<>(numSeats);
    private int numCustomers = 0;
    private Random random = new Random();

    public synchronized void enterShop(String customerName) throws InterruptedException {
        while (seats.size() >= numSeats) {
            System.out.println(customerName + " is waiting outside.");
            wait();
        }

        seats.add(customerName);
        numCustomers++;
        System.out.println(customerName + " has entered the shop and occupied a seat. Total customers: " + numCustomers);

        int eatingTime = 5; // Time to eat ice cream in seconds
        Thread.sleep(eatingTime * 1000);

        seats.remove(customerName);
        numCustomers--;
        System.out.println(customerName + " has finished eating and left the shop. Total customers: " + numCustomers);

        notifyAll();
    }

    public synchronized void placeOrder(String customerName) throws InterruptedException {
        while (!seats.contains(customerName)) {
            wait();
        }

        int orderTime = random.nextInt(6) + 3; // Time to place order in seconds
        Thread.sleep(orderTime * 1000);

        System.out.println(customerName + " has placed an order.");

        notifyAll();
    }
}