package exercise2;

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
            //Customer enters shop and wait 1-2 Min
            shop.enterShop(name);
            Thread.sleep(random.nextInt((2) + 1) * 1000);
            //Customer pace a Order and eat 5 Min
            shop.placeOrder(name);
            Thread.sleep(5000);
            //Customer leave Shop after eating
            shop.leaveShop(name);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}