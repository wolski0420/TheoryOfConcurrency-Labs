package agh.edu.pl.ex1;

import org.jcsp.lang.Alternative;
import org.jcsp.lang.CSProcess;
import org.jcsp.lang.Guard;
import org.jcsp.lang.One2OneChannelInt;

public class Consumer implements CSProcess {
    private final One2OneChannelInt[] buffer;
    private final Guard[] guards;
    private final Alternative alternative;
    private final int size;

    public Consumer(One2OneChannelInt[] buffer, int size) {
        this.buffer = buffer;
        this.guards = new Guard[buffer.length];
        this.alternative = new Alternative(guards);
        this.size = size;
    }

    @Override
    public void run() {
        for (int i = 0; i < buffer.length; i++) {
            guards[i] = buffer[i].in();
        }

        for (int i = 0; i < size; i++) {
            int index = alternative.select();
            System.out.println("Consumer received " + buffer[index].in().read() + " from " + index + " buffer node");
        }
    }
}
