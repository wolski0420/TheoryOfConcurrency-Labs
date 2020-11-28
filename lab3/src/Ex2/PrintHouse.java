package Ex2;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class PrintHouse implements IPrintService{
    private final ArrayList<Printer> printers;
    private final ReentrantLock lock;
    private final Condition cond;

    public PrintHouse(int number) {
        printers = new ArrayList<>();
        lock = new ReentrantLock();
        cond = lock.newCondition();
        init(number);
    }

    private void init(int number){
        for(int i=0; i<number; i++){
            printers.add(new Printer());
        }
    }

    private boolean allPrintersReserved(){
        for(Printer printer : printers) {
            if(!printer.isPrinting()){
                return false;
            }
        }

        return true;
    }

    @Override
    public int reserve(){
        lock.lock();
        try{
            while(allPrintersReserved()){
                cond.await();
            }

            for(int i=0; i<printers.size(); i++){
                if(!printers.get(i).isPrinting()){
                    printers.get(i).startPrinting();
                    return i;
                }
            }

        } catch(InterruptedException e){
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

        return -1;
    }

    @Override
    public void free(int index){
        lock.lock();
        try{
            if(allPrintersReserved()){
                cond.signalAll();
            }

            printers.get(index).finishPrinting();
        } catch(Exception e){
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
