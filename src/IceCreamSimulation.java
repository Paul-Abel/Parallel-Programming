public class IceCreamSimulation {
    public static void main(String[] args) {
        IceCreamShop shop = new IceCreamShop();

        Thread[] customers = new Thread[4];
        customers[0] = new Thread(new Customer("Alex", shop));
        customers[1] = new Thread(new Customer("Rabia", shop));
        customers[2] = new Thread(new Customer("Denis", shop));
        customers[3] = new Thread(new Customer("Ana", shop));

        for (Thread customer : customers) {
            customer.start();
        }
    }
}
