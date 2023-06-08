import java.util.Random;

class CustomerGenerator implements Runnable {
    private final IceCreamShop shop;
    private final Random random = new Random();
    private int totalCustumer = 0;

    public CustomerGenerator(IceCreamShop shop) {
        this.shop = shop;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int numCustomers = random.nextInt(5) + 3; // Random number of customers between 3 and 7
                for (int i = 0; i < numCustomers; i++) {
                    totalCustumer++;
                    String customerName = "Customer " + (totalCustumer);
                    Thread customerThread = new Thread(new Customer(customerName, shop));
                    customerThread.start();
                }

                Thread.sleep(6000); //Waiting 6 min before generating next set of customers
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}