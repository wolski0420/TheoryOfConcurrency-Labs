package Ex3;

public class Producer implements Runnable {
    private Buffer buffer;
    private final int ILOSC = 1;

    public Producer(Buffer buffer) {
        this.buffer = buffer;
    }

    public void run() {
        for(int i = 0;  i < ILOSC;   i++) {
            try{
                buffer.put("message "+i);
            } catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
