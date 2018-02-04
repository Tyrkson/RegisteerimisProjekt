package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import sample.Game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class FeedbackController {

    @FXML
    Label lBest, lAverage;

    @FXML
    AnchorPane rootPane;

    public void initialize(){
        setValues();
    }

    private void setValues() {
        ArrayList<Long> times = Game.getTimes();
        Collections.sort(times);
        lBest.setText(String.valueOf(times.get(0)));
        Long sum  = 0L;
        for(Long l : times){
            sum += l;
        }
        sum = sum/times.size();

        lAverage.setText(String.valueOf(sum));
    }

    @FXML
    public void loadMenuSheet() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("mein_menu.fxml"));
        rootPane.getChildren().setAll(pane);
    }
}
