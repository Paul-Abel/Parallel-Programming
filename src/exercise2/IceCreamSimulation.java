package exercise2;

import exercise2.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class IceCreamSimulation {
    public static void main(String[] args) {
        IceCreamShop shop = new IceCreamShop();
        CustomerGenerator customerGenerator = new CustomerGenerator(shop);

        // Create an ExecutorService using a fixed thread pool
        ExecutorService executor = Executors.newFixedThreadPool(1);

        // Execute the CustomerGenerator using the executor
        executor.execute(() -> customerGenerator.run());

    }
}
