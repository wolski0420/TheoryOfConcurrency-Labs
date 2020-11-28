package Ex2;

public class MainSemaphore {
    public int actualNumber;
    private final int maxNumber;

    public MainSemaphore(int maxNumber) {
        this.maxNumber = maxNumber;
        this.actualNumber = maxNumber;
    }

    public synchronized void acquire(){
        while(actualNumber == 0){
            try{
                wait();
            } catch(InterruptedException e){
                e.printStackTrace();
            }
        }

        this.actualNumber--;
//        notifyAll();
    }

    public synchronized void release(){
//        while(actualNumber == maxNumber){
//            try{
//                wait();
//            } catch(InterruptedException e){
//                e.printStackTrace();
//            }
//        }

        this.actualNumber++;
        notifyAll();
    }
}
