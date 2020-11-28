package Ex3;

import java.util.ArrayList;

public class Buffer {
    private final ArrayList<String> messages;
    private final int ILOSC = 1;

    public Buffer() {
        this.messages = new ArrayList<>();
    }

    public void put(String message) throws InterruptedException{
        synchronized (this){
            while(messages.size() == ILOSC){
                wait();
            }
            System.out.println("Wykonuej put");
            messages.add(message);
            notifyAll();
        }
    }

    public String take() throws InterruptedException{
        synchronized (this){
            while(messages.isEmpty()){
                wait();
            }
            System.out.println("Wykonuje take");
            String message = messages.get(0);
            messages.remove(0);

            notifyAll();

            return message;
        }
    }
}
