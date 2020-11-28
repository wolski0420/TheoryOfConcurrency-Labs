package Ex2a;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer implements IBufferService{
    private final int size;
    private int occupied;
    private final Lock lock;
    private final Condition busy;
    private final Condition free;
    private final ArrayList<Long> nanosPut;
    private final ArrayList<Long> nanosGet;

    public Buffer(int size) {
        this.size = size;
        this.occupied = 0;
        this.lock = new ReentrantLock();
        this.busy = lock.newCondition();
        this.free = lock.newCondition();
        this.nanosPut = new ArrayList<>();
        this.nanosGet = new ArrayList<>();
    }

    @Override
    public void put(int amount) throws InterruptedException {
        long start = System.nanoTime();

        lock.lock();
        try{
            while(amount > size - occupied){
                free.await();
            }

            occupied += amount;
            System.out.println("[Producer " + Thread.currentThread().getId() + "]   Occupied=" + occupied + "   Amount=" + amount);
            busy.signalAll();
        } finally {
            lock.unlock();
        }

        long finish = System.nanoTime();
        nanosPut.add(finish-start);
    }

    @Override
    public void get(int amount) throws InterruptedException {
        long start = System.nanoTime();

        lock.lock();
        try{
            while(amount > occupied){
                busy.await();
            }

            occupied -= amount;
            System.out.println("[Consumer " + Thread.currentThread().getId() + "]   Occupied=" + occupied + "   Amount=" + amount);
            free.signalAll();
        } finally {
            lock.unlock();
        }

        long finish = System.nanoTime();
        nanosGet.add(finish-start);
    }

    public ArrayList<Long> getNanosPut() {
        return nanosPut;
    }

    public ArrayList<Long> getNanosGet() {
        return nanosGet;
    }
}
