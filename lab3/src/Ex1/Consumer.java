package Ex1;

public class Consumer extends Thread {
    private final BoundedBuffer buffer;

    public Consumer(BoundedBuffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        for(int i=0; i<Main.ILOSC; i++) {
            try{
                String message = buffer.take();
                System.out.println("Thread " + Thread.currentThread().getId() + " has taken a " + message);
            } catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
