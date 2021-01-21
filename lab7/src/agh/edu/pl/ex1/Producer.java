package agh.edu.pl.ex1;

import org.jcsp.lang.Alternative;
import org.jcsp.lang.CSProcess;
import org.jcsp.lang.Guard;
import org.jcsp.lang.One2OneChannelInt;

import java.util.Random;

public class Producer implements CSProcess {
    private final One2OneChannelInt[] buffer;
    private final One2OneChannelInt[] more;
    private final Guard[] guards;
    private final Alternative alternative;
    private final int size;
    private final Random random;

    public Producer(One2OneChannelInt[] buffer, One2OneChannelInt[] more, int size) {
        this.buffer = buffer;
        this.more = more;
        this.guards = new Guard[more.length];
        this.alternative = new Alternative(guards);
        this.size = size;
        this.random = new Random();
    }

    @Override
    public void run() {
        for(int i=0; i<more.length; i++){
            guards[i] = more[i].in();
        }

        for(int i=0; i<size; i++){
            int index = alternative.select();
            more[index].in().read();
            int rand = random.nextInt(100)+1;
            System.out.println("Producer inserted " + rand + " to the " + index + " buffer node");
            buffer[index].out().write(rand);
        }
    }
}
