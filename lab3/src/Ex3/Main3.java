package Ex3;

import java.util.ArrayList;

public class Main3 {
    public static void main(String[] args) {
        ArrayList<Guest> guests = new ArrayList<>();
        Waiter waiter = new Waiter();
        int pairNo = 5;

        for(int i=0; i<pairNo; i++){
            guests.add(new Guest(i, waiter));
            guests.add(new Guest(i, waiter));
        }

        for(Guest guest : guests){
            guest.start();
        }
    }
}
