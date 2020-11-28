package Ex1;

import java.util.ArrayList;

public class Main {
    public static int ILOSC = 5;

    public static void main(String [] args){
        ArrayList<Producer> producers = new ArrayList<>();
        ArrayList<Consumer> consumers = new ArrayList<>();
        BoundedBuffer buffer = new BoundedBuffer();

        for(int i=0; i<5; i++){
            producers.add(new Producer(buffer));
            consumers.add(new Consumer(buffer));
        }

        for(int i=0; i<5; i++){
            producers.get(i).start();
            consumers.get(i).start();
        }
    }
}
