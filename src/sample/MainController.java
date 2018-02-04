package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MainController {



    @FXML
    private AnchorPane rootPane;

    @FXML
    private ImageView ivWhitePlay, ivWhiteExit;

    private GameMeediaPlayer a;

    public void initialize(){
        a = new GameMeediaPlayer("PAtchimoo.mp3");
        a.play();
    }

    @FXML
    public void loadGameSheet() throws IOException{
        a.stop();
        AnchorPane pane = FXMLLoader.load(getClass().getResource("countdown.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    @FXML
    public void visible(){
        ivWhitePlay.setVisible(true);
    }

    @FXML
    public void notVisible(){
        ivWhitePlay.setVisible(false);
    }

    @FXML
    public void closeGame(){
        Platform.exit();
    }

    @FXML
    public void visibleExit(){
        ivWhiteExit.setVisible(true);
    }

    @FXML
    public void notVisibleExit(){
        ivWhiteExit.setVisible(false);
    }
}
