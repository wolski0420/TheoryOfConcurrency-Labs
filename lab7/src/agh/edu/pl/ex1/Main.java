package agh.edu.pl.ex1;

import org.jcsp.lang.CSProcess;
import org.jcsp.lang.One2OneChannelInt;
import org.jcsp.lang.Parallel;
import org.jcsp.lang.StandardChannelIntFactory;

public class Main {
    public static void main(String[] args) {
        StandardChannelIntFactory standardChannelFactory = new StandardChannelIntFactory();

        int bufferNodesNumber = 25;
        int productsNumber = 50;

        One2OneChannelInt[] producerToBuffer = standardChannelFactory.createOne2One(bufferNodesNumber);
        One2OneChannelInt[] bufferToProducer = standardChannelFactory.createOne2One(bufferNodesNumber);
        One2OneChannelInt[] bufferToConsumer = standardChannelFactory.createOne2One(bufferNodesNumber);

        CSProcess producer = new Producer(producerToBuffer, bufferToProducer, productsNumber);
        CSProcess consumer = new Consumer(bufferToConsumer, productsNumber);
        CSProcess[] bufferNodes = new CSProcess[bufferNodesNumber];
        for (int i = 0; i < bufferNodesNumber; i++) {
            bufferNodes[i] = new BufferNode(producerToBuffer[i], bufferToConsumer[i], bufferToProducer[i]);
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
