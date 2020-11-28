package Ex3;

import java.util.HashMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Waiter implements IWaiterService{
    private final HashMap<Integer, Condition> waiting;
    private final ReentrantLock lock;
    private final Condition tableBusy;
    private int peopleNoAtTheTable;
    private int pairIndAtTheTable;

    public Waiter() {
        this.waiting = new HashMap<>();
        this.lock = new ReentrantLock();
        this.tableBusy = lock.newCondition();
        this.peopleNoAtTheTable = 0;
        this.pairIndAtTheTable = -1;
    }

    @Override
    public void wantTable(int index) {
        lock.lock();
        try{
            if(waiting.containsKey(index)){
                System.out.println("Pair=" + index + ": second of the guests has already come!");
                waiting.get(index).signal();
                waiting.remove(index);
            }
            else{
                System.out.println("Pair=" + index + ": one of the guests started waiting for the second one...");
                waiting.put(index, lock.newCondition());
                waiting.get(index).await();
            }

            System.out.println("Pair=" + index + ": waiting for free table...");
            while(peopleNoAtTheTable > 0 && pairIndAtTheTable != index){
                tableBusy.await();
            }
            System.out.println("Pair=" + index + ": table is already free! Sitting now!");

            pairIndAtTheTable = index;
            peopleNoAtTheTable++;

            if(peopleNoAtTheTable == 2){
                System.out.println("Pair=" + index + ": pair is sitting at the table, started eating!");
            }
        } catch(InterruptedException e){
            e.printStackTrace();
        } finally{
            lock.unlock();
        }
    }

    @Override
    public void freeTable() {
        lock.lock();
        try{
            peopleNoAtTheTable--;
            System.out.println("Someone finished eating, now " + peopleNoAtTheTable + " guests at the table...");
            if(peopleNoAtTheTable == 0) {
                tableBusy.signalAll();
            }
        } catch(Exception e){
            e.printStackTrace();
        } finally{
            lock.unlock();
        }
    }
}
