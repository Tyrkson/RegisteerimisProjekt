package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class GameStopper {

    private static long startTime;
    private static long time;
    private static boolean stopperState;

    private GameStopper(){}

    public static void start(){
        stopperState = true;
        startTime = System.currentTimeMillis();
    }

    public static void stop(){
        time = System.currentTimeMillis()-startTime;
        stopperState = false;
    }

    public static boolean isRunning(){
        return stopperState;
    }


    public static long getTime(){
        return System.currentTimeMillis()-startTime;
    }
}
