package agh.edu.pl.ex2;

import org.jcsp.lang.CSProcess;
import org.jcsp.lang.One2OneChannelInt;

public class Consumer implements CSProcess {
    private final One2OneChannelInt buffer;
    private final int size;

    public Consumer(One2OneChannelInt buffer, int size) {
        this.buffer = buffer;
        this.size = size;
    }

    @Override
    public void run() {
        for (int i = 0; i < size; i++) {
            System.out.println("Consumer received " + buffer.in().read() + " from buffer");
        }
    }
}
