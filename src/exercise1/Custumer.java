package exercise1;

import java.util.Random;

class Customer implements Runnable {
    private final String name;
    private final IceCreamShop shop;
    private final Random random = new Random();

    public Customer(String name, IceCreamShop shop) {
        this.name = name;
        this.shop = shop;
    }


    @Override
    public void run() {
        try {
            shop.enterShop(name);
            Thread.sleep(random.nextInt((2) + 1) * 1000);
            shop.placeOrder(name);
            Thread.sleep(5000);
            shop.leaveShop(name);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}