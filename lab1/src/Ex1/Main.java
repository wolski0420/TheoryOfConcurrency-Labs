package Ex1;

public class Main {
    public static void main(String [] args){
        Counter counter = new Counter(0);
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
