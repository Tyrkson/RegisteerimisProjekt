package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;

public class CountdownController {

    @FXML
    public ImageView iv1, iv2, iv3, iv4;

    @FXML
    public Label lStart;

    @FXML
    public AnchorPane rootPane;

    private ArrayList<ImageView> ivs;

    Timeline timeline;
    final int seconds = 4;
    private GameMeediaPlayer songPlayer;

    public void initialize(){
        //startPlayer();
        collectIvs();
        startImageAnimation();

    }

    private void collectIvs() {
        ivs = new ArrayList<>();
        ivs.add(iv1);
        ivs.add(iv2);
        ivs.add(iv3);
        ivs.add(iv4);

    }

    private void startPlayer() {
        songPlayer = new GameMeediaPlayer("muudjum.mp3");
        songPlayer.play();
    }

    private void startImageAnimation() {
        timeline = new Timeline(new KeyFrame(Duration.seconds(seconds), e->{
            if(iv4.isVisible()){
                startGame();
            }
            makeVisible();
        }));
        timeline.setCycleCount(5);
        timeline.play();
    }

    private void startGame() {
        lStart.setVisible(true);
        try {
            Thread.sleep(100);
            loadUnlockScreenSheet();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadUnlockScreenSheet() throws IOException {
        Game.gameStarted();
        AnchorPane pane = FXMLLoader.load(getClass().getResource("unlock_screen.fxml"));
        GameStopper.start();
        rootPane.getChildren().setAll(pane);
    }

    private void makeVisible() {
        for(ImageView iv: ivs){
            if(!iv.isVisible()){
                iv.setVisible(true);
                break;
            }
        }
    }
}
