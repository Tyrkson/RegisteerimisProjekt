package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MainController {

    @FXML
    private AnchorPane rootPane;

    @FXML
    public void loadGameSheet() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("game.fxml"));
        GameStopper.start();
        rootPane.getChildren().setAll(pane);
    }
}
