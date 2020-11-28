package Ex1;

public class Producer extends Thread {
    private final BoundedBuffer buffer;

    public Producer(BoundedBuffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        for(int i=0; i<Main.ILOSC; i++) {
            try{
                buffer.put("message "+i);
            } catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
