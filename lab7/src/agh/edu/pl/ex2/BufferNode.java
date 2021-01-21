package agh.edu.pl.ex2;

import org.jcsp.lang.CSProcess;
import org.jcsp.lang.One2OneChannelInt;

public class BufferNode implements CSProcess {
    private final One2OneChannelInt input;
    private final One2OneChannelInt output;

    public BufferNode(One2OneChannelInt input, One2OneChannelInt output) {
        this.input = input;
        this.output = output;
    }

    @Override
    public void run() {
        while(true){
            output.out().write(input.in().read());
        }
    }
}
