

public class Service implements Runnable {
    private final String name;
    private final IceCreamShop shop;


    public Service(String name, IceCreamShop shop) {
        this.name = name;
        this.shop = shop;
    }

    @Override
    public void run() {
        while (true) {
            try {
                shop.serveOrder(name);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}

