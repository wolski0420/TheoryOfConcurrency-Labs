package Ex1;

public class IncrThread extends Thread{
    private final Counter counter;

    public IncrThread(Counter counter) {
        super();
        this.counter = counter;
    }

    @Override
    public void run() {
        for(int i=0; i<100000000; i++){
            this.counter.increment();
        }
    }
}
