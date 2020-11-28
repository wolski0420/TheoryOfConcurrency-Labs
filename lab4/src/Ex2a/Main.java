package Ex2a;

import java.util.ArrayList;

public class Main {
    private final static int M = 5;
    private final static int producerNo = 4;
    private final static int consumerNo = 3;
    private final static int loopsNo = 1000;
    private final static ArrayList<Producer> producers = new ArrayList<>();
    private final static ArrayList<Consumer> consumers = new ArrayList<>();

    public static void main(String[] args) {
        Buffer buffer = new Buffer(2*M);
        for(int i=0; i<producerNo; i++)
            producers.add(new Producer(buffer, M, loopsNo));

        for(int i=0; i<consumerNo; i++)
            consumers.add(new Consumer(buffer, M, loopsNo));

        producers.forEach(Thread::start);
        consumers.forEach(Thread::start);

        try{
//            for(Producer producer: producers)
//                producer.join();
            for(Consumer consumer: consumers)
                consumer.join();
            System.exit(0);
        } catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
