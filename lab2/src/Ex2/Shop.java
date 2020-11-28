package Ex2;

import java.util.ArrayList;

public class Shop {
    private final int allBaskets;
    private final ArrayList<Customer> customers;
    private final MainSemaphore semaphore;

    public Shop(int allBaskets) {
        this.allBaskets = allBaskets;
        this.customers = new ArrayList<>();
        this.semaphore = new MainSemaphore(allBaskets);
    }

    public void enter(Customer customer){
        this.semaphore.acquire();
        this.customers.add(customer);
        System.out.println("Customer " + customer + " entered the shop. Baskets=" + semaphore.actualNumber + ", Customers=" + customers.size());
    }

    public void leave(Customer customer){
        this.semaphore.release();
        this.customers.remove(customer);
        System.out.println("Customer " + customer + " leaved the shop. Baskets=" + semaphore.actualNumber + ", Customers=" + customers.size());
    }
}
