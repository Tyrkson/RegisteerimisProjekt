package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class GameStopper {

    private static Timeline timeline;
    private static int timeInSecs;
    private static boolean stopperState;

    private GameStopper(){}

    public static void start(){
        stopperState = true;
        timeInSecs = 0;
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), e ->{
            updateTime();
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public static void stop(){
        timeline.stop();
        stopperState = false;
    }

    public static boolean isRunning(){
        return stopperState;
    }

    private static void updateTime() {
        timeInSecs++;
    }

    public static int getTimeInSecs(){
        return timeInSecs;
    }
}
