package Ex1;

public class Processor extends Thread{
    private final IBufferService buffer;
    private final int id;
    private final int loopsNo;

    public Processor(IBufferService buffer, int id, int loops) {
        this.buffer = buffer;
        this.id = id;
        this.loopsNo = loops;
    }

    @Override
    public void run() {
        super.run();

        for(int i=0; i<loopsNo; i++){
            try{
                buffer.process(id);
            } catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
