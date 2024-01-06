package exercise2;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// Generates new Customer for the Shop
class CustomerGenerator implements Runnable {
    private final IceCreamShop shop;
    private final Random random = new Random();
    private int totalCustomer = 0;
    private final ExecutorService executor;

    public CustomerGenerator(IceCreamShop shop) {
        this.shop = shop;
        this.executor = Executors.newCachedThreadPool();
    }

    @Override
    public void run() {
        try {
            while (true) {
                int numCustomers = random.nextInt(5) + 3; // Random number of customers between 3 and 7
                for (int i = 0; i < numCustomers; i++) {
                    totalCustomer++;
                    String customerName = "Customer " + totalCustomer;
                    executor.execute(() -> new Customer(customerName, shop).run());
                }
                Thread.sleep(6000); //Waiting 6 min before generating next set of customers
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}