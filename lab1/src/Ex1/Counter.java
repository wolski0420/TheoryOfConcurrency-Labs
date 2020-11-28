package Ex1;

public class Counter {
    private int number;

    public Counter(int number) {
        this.number = number;
    }

    public void increment(){
        this.number++;
    }

    public void decrement(){
        this.number--;
    }

    @Override
    public String toString() {
        return "number=" + number;
    }
}
