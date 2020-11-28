package Ex2;

import java.util.Random;

public class User extends Thread{
    private final int id;
    private final IPrintService service;

    public User(int id, IPrintService service) {
        this.id = id;
        this.service = service;
    }

    @Override
    public void run() {
        super.run();
        int printerId = service.reserve();

        System.out.println("User " + id + " is printing on " + printerId + " printer");
        try{
            Thread.sleep(new Random().nextLong()%2000 + 2000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        service.free(printerId);
    }
}
