package Ex2;

import java.util.ArrayList;

public class Main2 {
    public static void main(String[] args) {
        PrintHouse printHouse = new PrintHouse(5);
        ArrayList<User> users = new ArrayList<>();

        for(int i=0; i<15; i++){
            users.add(new User(i, printHouse));
        }

        for(User user: users){
            user.start();
        }
    }
}
