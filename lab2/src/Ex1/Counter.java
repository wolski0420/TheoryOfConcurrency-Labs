package Ex1;

public class Counter {
    private int number;
    private final BinarySemaphore semaphore;

    public Counter(int number, BinarySemaphore semaphore) {
        this.number = number;
        this.semaphore = semaphore;
    }

    public void increment(){
        this.semaphore.acquire();
        this.number++;
        this.semaphore.release();
    }

    public void decrement(){
        this.semaphore.acquire();
        this.number--;
        this.semaphore.release();
    }

    @Override
    public String toString() {
        return "number=" + number;
    }
}
