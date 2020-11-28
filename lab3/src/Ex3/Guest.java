package Ex3;

import java.util.Random;

public class Guest extends Thread{
    private final int pairIndex;
    private final IWaiterService service;
    private final Random random;

    public Guest(int pairIndex, IWaiterService service) {
        this.pairIndex = pairIndex;
        this.service = service;
        this.random = new Random();
    }

    @Override
    public void run() {
        super.run();
        try{
            sleep(random.nextLong()%2000 + 2000);
            service.wantTable(pairIndex);
            sleep(random.nextLong()%2000 + 2000);
            service.freeTable();
        } catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
