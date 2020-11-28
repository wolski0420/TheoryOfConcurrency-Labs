package Ex2;

import java.util.ArrayList;

public class Main2 {
    public static void main(String[] args) {
        Shop shop = new Shop(5);
        ArrayList<Customer> customers = new ArrayList<>();

        for(int i=0; i<10; i++)
            customers.add(new Customer(i));

        customers.forEach((customer -> customer.shopping(shop)));
    }
}
