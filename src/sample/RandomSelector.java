package sample;

import java.util.ArrayList;
import java.util.Random;

public class RandomSelector {

    private RandomSelector(){}

    public static int select(ArrayList aList){
        Random random = new Random();
        int randIndex;

        if(aList.size()>1) {
            randIndex = random.nextInt(aList.size() - 1);
        }else{
            randIndex = 0;
        }

        return randIndex;
    }
}
