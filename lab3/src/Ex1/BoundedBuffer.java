package Ex1;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BoundedBuffer {
    private final ArrayList<String> messages;
    private final Lock lock;
    private final Condition notFull;
    private final Condition notEmpty;

    public BoundedBuffer() {
        this.messages = new ArrayList<>();
        this.lock = new ReentrantLock();
        this.notFull = this.lock.newCondition();
        this.notEmpty = this.lock.newCondition();
    }

    public void put(String message) throws InterruptedException{
        lock.lock();
        System.out.println("Thread " + Thread.currentThread().getId() + " is trying to put a " + message);
        try{
            while(messages.size() == Main.ILOSC){
                notFull.await();
            }

            messages.add(message);
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
        System.out.println("Thread " + Thread.currentThread().getId() + " has put a " + message);
    }

    public String take() throws InterruptedException{
        lock.lock();
        System.out.println("Thread " + Thread.currentThread().getId() + " is trying to take a message from buffer");
        try{
            while(messages.isEmpty()){
                notEmpty.await();
            }

            String message = messages.get(0);
            messages.remove(0);
            notFull.signal();
            return message;
        } finally {
            lock.unlock();
        }
    }
}
