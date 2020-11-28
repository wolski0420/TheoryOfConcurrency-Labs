package Ex2;

public class Counter {
    private int number;

    public Counter(int number) {
        this.number = number;
    }

    public synchronized void increment(){
        this.number++;
        System.out.println("Increment: " + number);
    }

    public synchronized void decrement(){
        this.number--;
        System.out.println("Decrement: " + number);
    }

    @Override
    public String toString() {
        return "number=" + number;
    }
}
