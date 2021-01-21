package agh.edu.pl.ex2;

import org.jcsp.lang.CSProcess;
import org.jcsp.lang.One2OneChannelInt;

import java.util.Random;

public class Producer implements CSProcess {
    private final One2OneChannelInt buffer;
    private final int size;
    private final Random random;

    public Producer(One2OneChannelInt buffer, int size) {
        this.buffer = buffer;
        this.size = size;
        this.random = new Random();
    }

    @Override
    public void run() {
        for(int i=0; i<size; i++){
            int rand = random.nextInt(100)+1;
            System.out.println("Producer inserted " + rand + " to the buffer");
            buffer.out().write(rand);
        }
    }
}
