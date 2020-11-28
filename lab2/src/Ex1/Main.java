package Ex1;

public class Main {
    public static void main(String [] args){
        BinarySemaphore semaphore = new BinarySemaphore();
        Counter counter = new Counter(0, semaphore);
        IncrThread incrThread = new IncrThread(counter);
        DecrThread decrThread = new DecrThread(counter);

        try{
            incrThread.start();
            decrThread.start();
            incrThread.join();
            decrThread.join();
        } catch(Exception e){
            e.printStackTrace();
        }

        System.out.println(counter);
    }
}
