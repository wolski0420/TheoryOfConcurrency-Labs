package Ex2a;

import java.util.Random;

public class Producer extends Thread{
    private final int maxRand;
    private final int loopsNo;
    private final Random random;
    private final IBufferService bufferService;

    public Producer(IBufferService bufferService, int maxRand, int loopsNo) {
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
                System.out.println("Producer thread " + currentThread().getId() + " is trying to put " + amount +
                        " to the buffer! [i=" + i + "]");
                bufferService.put(amount);
            } catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
