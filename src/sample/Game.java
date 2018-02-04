package sample;

import java.util.ArrayList;

public class Game {

    private static int roundsToPlay = 2;
    private static ArrayList<Long> times;
    private static int finishedRounds;

    private Game(){}

    public static void gameStarted(){
        GameStopper.start();
        finishedRounds = 0;
        times = new ArrayList<>();
    }

    public static void finishedRound(){
        GameStopper.stop();
        times.add(GameStopper.getTime());
        finishedRounds++;
    }

    public static boolean isOver(){
        return finishedRounds == roundsToPlay;
    }


    public static void newRound() {
        GameStopper.start();
    }
}
