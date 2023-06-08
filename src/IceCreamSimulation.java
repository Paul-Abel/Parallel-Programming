public class IceCreamSimulation {
    public static void main(String[] args) {
        IceCreamShop shop = new IceCreamShop();

        Thread customerGeneratorThread = new Thread(new CustomerGenerator(shop));
        customerGeneratorThread.start();


    }
}
