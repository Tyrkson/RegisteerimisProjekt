package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GameController {

    private ArrayList<String> laused;

    private String selectedLause;

    private String file = "andmed.txt";

    Timeline timeline, inputPaneAnimation;

    private int globalPace = -15;

    @FXML
    Label LRequestedInput, LResult, lTime;

    @FXML
    TextField TfInput;

    @FXML
    public void checkUserInput(){
        if(TfInput.getText().toString().equals(selectedLause)){
            showResult("Õige");
        }else{
            showResult("Vale");
        }
        if(laused.isEmpty()){
            showResult("Sõnad otsas");
            if(GameStopper.isRunning()) {
                stopTimer();
                lTime.setText(String.valueOf(GameStopper.getTimeInSecs()));
            }
        }else {
            selectData();
            showRequestInput();
        }
    }

    private void stopTimer() {
        GameStopper.stop();
    }


    private void showResult(String result) {
        LResult.setText(result);
    }

    public void initialize(){
        loadDataFromFileToList();
        selectData();
        showRequestInput();
    }



    private void showRequestInput() {
        LRequestedInput.setText(selectedLause);
    }

    private void selectData() {
        selectedLause = laused.remove(RandomSelector.select(laused));
    }

    private void loadDataFromFileToList() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            laused = new ArrayList<>();

            while(line != null){
                laused.add(line);
                line = br.readLine();
            }

        } catch (FileNotFoundException e) {
            laused = new ArrayList<>();
        } catch (IOException e) {
            laused = new ArrayList<>();
        }

    }

    @FXML
    private Pane inputPane;

    @FXML
    private AnchorPane gamePane;

    @FXML
    public void executeInputPaneAnimation(){

        if(inputPaneAnimation != null && inputPaneAnimation.getStatus() == Animation.Status.RUNNING){
            stopInputPaneAnimation();
        }

        globalPace *= -1;

        changeInputPaneState();
    }

    private void changeInputPaneState() {
        inputPaneAnimation = new Timeline(new KeyFrame(Duration.millis(15), e->{
            double newWidth = inputPane.getWidth() + globalPace;
            inputPane.setMinWidth(newWidth);

            if(newWidth >= gamePane.getWidth() || newWidth <= 0){
                if(newWidth > gamePane.getWidth()){
                    inputPane.setMinWidth(gamePane.getWidth());
                }
                if(newWidth < 0){
                    inputPane.setMinWidth(0);
                }
                stopInputPaneAnimation();
            }
        }));
        inputPaneAnimation.setCycleCount(Animation.INDEFINITE);
        inputPaneAnimation.play();
    }

    private void stopInputPaneAnimation() {
        inputPaneAnimation.stop();
    }


}
