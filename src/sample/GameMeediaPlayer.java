package sample;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.nio.file.Paths;

public class GameMeediaPlayer{
    private Media mediasong;
    private MediaPlayer mediaplayer;

    GameMeediaPlayer(String songName){
        mediasong = new Media(Paths.get("src/sample/Introsong/" + songName).toUri().toString());
        mediaplayer = new MediaPlayer(mediasong);

    }

    public void play() {
        mediaplayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaplayer.play();
    }

    public void stop() {

        mediaplayer.stop();
    }

}