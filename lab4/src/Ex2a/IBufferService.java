package Ex2a;

public interface IBufferService {

    void put(int amount) throws InterruptedException;

    void get(int amount) throws InterruptedException;
}
