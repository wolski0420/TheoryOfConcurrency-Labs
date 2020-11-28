package Ex1;

public interface IBufferService {

    void produce() throws InterruptedException;

    void process(int processor) throws InterruptedException;

    void consume() throws InterruptedException;
}
