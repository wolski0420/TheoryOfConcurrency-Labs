package Ex1;

public class Producer extends Thread{
    private final IBufferService buffer;
    private final int loopsNo;

    public Producer(IBufferService buffer, int loops) {
        this.buffer = buffer;
        this.loopsNo = loops;
    }

    @Override
    public void run() {
        super.run();

        for(int i=0; i<loopsNo; i++){
            try{
                buffer.produce();
            } catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
