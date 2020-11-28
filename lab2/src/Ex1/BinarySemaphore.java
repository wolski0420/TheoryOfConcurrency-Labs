package Ex1;

public class BinarySemaphore {
    private boolean state;

    public BinarySemaphore() {
        this.state = true;
    }

    public synchronized void acquire(){
        while(isAcquired()){
            try{
                wait();
            } catch(InterruptedException e){
                e.printStackTrace();
            }
        }

        this.state = false;
    }

    public synchronized void release(){
        this.state = true;
        notifyAll();
    }

    public boolean isAcquired(){
        return !this.state;
    }
}
