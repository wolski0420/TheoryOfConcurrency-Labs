package Ex1;

import java.util.Arrays;
import java.util.concurrent.Semaphore;

public class Buffer implements IBufferService{
    private final int size;
    private final int processors;
    private final int[] array;
    private final Semaphore[] processorSem;
    private final Semaphore completedSem;
    private final Semaphore consumerSem;
    private final Semaphore accessSem;
    private final Semaphore accessProdSem;
    private final Semaphore accessProcSem;
    private final Semaphore accessConsSem;

    public Buffer(int size, int processors){
        this.size = size;
        this.processors = processors;
        this.array = new int[size];
        this.processorSem = new Semaphore[processors];
        this.completedSem = new Semaphore(0);
        this.consumerSem = new Semaphore(size);
        this.accessSem = new Semaphore(1);
        this.accessProdSem = new Semaphore(1);
        this.accessProcSem = new Semaphore(1);
        this.accessConsSem = new Semaphore(1);
        init();
    }

    private void init(){
        Arrays.fill(array, -1);
        for(int i=0; i<processors; i++){
            processorSem[i] = new Semaphore(0);
        }
    }

    @Override
    public void produce() throws InterruptedException {
        consumerSem.acquire();

        accessProdSem.acquire();
        for(int i=0; i<size; i++) {
            if (array[i] == -1) {
                array[i]++;
                break;
            }
        }
        accessProdSem.release();

        accessSem.acquire();
        print();
        accessSem.release();

        processorSem[0].release();
    }

    @Override
    public void process(int processor) throws InterruptedException {
        processorSem[processor].acquire();

        accessProcSem.acquire();
        for(int i=0; i<size; i++){
            if(array[i] == processor){
                array[i]++;
                break;
            }
        }
        accessProcSem.release();

        accessSem.acquire();
        print();
        accessSem.release();

        if(processor == processors-1){
            completedSem.release();
        } else{
            processorSem[processor+1].release();
        }
    }

    @Override
    public void consume() throws InterruptedException {
        completedSem.acquire();

        accessConsSem.acquire();
        for (int i=0; i<size; i++){
            if (array[i] == processors) {
                array[i] = -1;
                break;
            }
        }
        accessConsSem.release();

        accessSem.acquire();
        print();
        accessSem.release();

        consumerSem.release();
    }

    private void print(){
        for(int i=0; i<size; i++){
            System.out.print(array[i] + " ");
        }

        System.out.println("");
    }
}
