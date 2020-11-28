package Ex1;

public class DecrThread extends Thread{
    private final Counter counter;

    public DecrThread(Counter counter) {
        super();
        this.counter = counter;
    }

    @Override
    public void run() {
        for(int i=0; i<100000000; i++){
            this.counter.decrement();
        }
    }
}
