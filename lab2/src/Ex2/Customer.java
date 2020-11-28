package Ex2;

import java.util.Random;

public class Customer extends Thread{
    private final int id;
    private Shop currentShop;

    public Customer(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        this.currentShop.enter(this);

        try{
            Thread.sleep(new Random().nextLong()%3000+3000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        this.currentShop.leave(this);
    }

    public void shopping(Shop shop){
        this.currentShop = shop;
        start();
    }

    @Override
    public String toString() {
        return "" + this.id;
    }
}
