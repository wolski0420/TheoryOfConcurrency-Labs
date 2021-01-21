package agh.edu.pl.ex2;

import org.jcsp.lang.CSProcess;
import org.jcsp.lang.One2OneChannelInt;
import org.jcsp.lang.Parallel;
import org.jcsp.lang.StandardChannelIntFactory;

public class Main {
    public static void main(String[] args) {
        StandardChannelIntFactory standardChannelFactory = new StandardChannelIntFactory();

        int bufferNodesNumber = 5;
        int productsNumber = 50;

        One2OneChannelInt producerToBuffer = standardChannelFactory.createOne2One();
        One2OneChannelInt[] buffer = standardChannelFactory.createOne2One(bufferNodesNumber-1);
        One2OneChannelInt bufferToConsumer = standardChannelFactory.createOne2One();

        CSProcess producer = new Producer(producerToBuffer, productsNumber);
        CSProcess consumer = new Consumer(bufferToConsumer, productsNumber);
        CSProcess[] bufferNodes = new CSProcess[bufferNodesNumber];
        bufferNodes[0] = new BufferNode(producerToBuffer, buffer[0]);
        bufferNodes[bufferNodesNumber-1] = new BufferNode(buffer[bufferNodesNumber-2], bufferToConsumer);

        for (int i = 1; i < bufferNodesNumber-1; i++) {
            bufferNodes[i] = new BufferNode(buffer[i-1], buffer[i]);
        }

        CSProcess[] allProcesses = new CSProcess[bufferNodesNumber+2];
        allProcesses[0] = producer;
        allProcesses[1] = consumer;
        for (int i = 2; i < bufferNodesNumber+2; i++) {
            allProcesses[i] = bufferNodes[i-2];
        }

        Parallel parallel = new Parallel(allProcesses);
        parallel.run();
    }
}
