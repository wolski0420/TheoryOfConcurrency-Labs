package agh.edu.pl.ex1;

import org.jcsp.lang.CSProcess;
import org.jcsp.lang.One2OneChannelInt;

public class BufferNode implements CSProcess {
    private final One2OneChannelInt input;
    private final One2OneChannelInt output;
    private final One2OneChannelInt more;

    public BufferNode(One2OneChannelInt input, One2OneChannelInt output, One2OneChannelInt more) {
        this.input = input;
        this.output = output;
        this.more = more;
    }

    @Override
    public void run() {
        while(true){
            more.out().write(0);
            output.out().write(input.in().read());
        }
    }
}
