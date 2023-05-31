import java.util.Random;

class Customer implements Runnable {
    private String name;
    private IceCreamShop shop;
    private Random random = new Random();

    public Customer(String name, IceCreamShop shop) {
        this.name = name;
        this.shop = shop;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep((random.nextInt(2) + 1) * 1000); // Random wait time before entering shop
                shop.enterShop(name);
                shop.placeOrder(name);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}