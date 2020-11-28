package Ex2a;

import java.util.Random;

public class Consumer extends Thread{
    private final int maxRand;
    private final int loopsNo;
    private final Random random;
    private final IBufferService bufferService;

    public Consumer(IBufferService bufferService, int maxRand, int loopsNo) {
        this.maxRand = maxRand;
        this.loopsNo = loopsNo;
        this.random = new Random();
        this.bufferService = bufferService;
    }

    @Override
    public void run() {
        super.run();

        for(int i=0; i<loopsNo; i++){
            try{
                int amount = random.nextInt(maxRand);
                System.out.println("Consumer thread " + currentThread().getId() + " is trying to get " + amount +
                        " from the buffer! [i=" + i + "]");
                bufferService.get(amount);
            } catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
