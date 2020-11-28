package Ex3;

import java.util.ArrayList;

public class Main3 {
    public static void main(String [] args){
        ArrayList<Producer> producers = new ArrayList<>();
        ArrayList<Consumer> consumers = new ArrayList<>();
        Buffer buffer = new Buffer();

        for(int i=0; i<5; i++){
            producers.add(new Producer(buffer));
            consumers.add(new Consumer(buffer));
        }

        for(int i=0; i<5; i++){
            producers.get(i).run();
            consumers.get(i).run();
        }
    }
}
