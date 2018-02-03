package sample;

import java.util.Random;

public class RandomSelector {

    private RandomSelector(){}

    public static int select(int size){
        Random random = new Random();
        int randIndex;

        if(size > 1) {
            randIndex = random.nextInt(size);
        }else{
            randIndex = 0;
        }

        return randIndex;
    }
}
