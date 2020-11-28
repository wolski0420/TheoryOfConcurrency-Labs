package Ex3;

public class Consumer implements Runnable {
    private Buffer buffer;
    private final int ILOSC = 1;

    public Consumer(Buffer buffer) {
        this.buffer = buffer;
    }

    public void run() {
        for(int i = 0;  i < ILOSC;   i++) {
            try{
                String message = buffer.take();
                System.out.println(message);
            } catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
