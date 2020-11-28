package Ex1;

public class Main {
    private final static int size = 10;
    private final static int processorsNo = 5;
    private final static int loops = 1000;

    public static void main(String[] args){
        IBufferService buffer = new Buffer(size,processorsNo);
        Producer producer = new Producer(buffer, loops);
        Consumer consumer = new Consumer(buffer, loops);
        Processor[] processors = new Processor[processorsNo];
        for(int i=0; i<processorsNo; i++)
            processors[i] = new Processor(buffer, i, loops);

        try{
            producer.start();
            consumer.start();
            for(int i=0; i<processorsNo; i++)
                processors[i].start();

            producer.join();
            consumer.join();
            for(int i=0; i<processorsNo; i++)
                processors[i].join();
        } catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
