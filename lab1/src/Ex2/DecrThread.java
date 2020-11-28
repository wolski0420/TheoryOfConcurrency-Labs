package Ex2;

public class DecrThread extends Thread{
    private final Counter counter;

    public DecrThread(Counter counter) {
        super();
        this.counter = counter;
    }

    @Override
    public void run() {
        for(int i=0; i<1000; i++){
            this.counter.decrement();
        }
    }
}
