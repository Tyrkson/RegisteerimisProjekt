package sample;

import javafx.application.Application;
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

    @FXML
    public void loadGameSheet() throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("unlock_screen.fxml"));
        GameStopper.start();
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
