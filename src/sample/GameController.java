package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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

    private long startTimeinMillis, timeNow;

    private Timeline timeline;

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
            stopTimer();
        }else {
            selectData();
            showRequestInput();
        }
    }

    private void stopTimer() {
        timeline.stop();
    }

    private void showResult(String result) {
        LResult.setText(result);
    }

    public void initialize(){
        loadDataFromFileToList();
        selectData();
        showRequestInput();
        createTimer();
    }

    private void createTimer() {
        startTimeinMillis = System.currentTimeMillis();

        timeline = new Timeline(new KeyFrame(Duration.seconds(1), e ->{
            updateTime(getTime());
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();


    }

    private int getTime() {
        timeNow = System.currentTimeMillis();
        return (int) (timeNow-startTimeinMillis)/1000;
    }

    private void updateTime(int time) {
        lTime.setText(String.valueOf(time));
    }

    private void showRequestInput() {
        LRequestedInput.setText(selectedLause);
    }

    private void selectData() {
        Random random = new Random();
        int randIndex;

        if(laused.size()>1) {
            randIndex = random.nextInt(laused.size() - 1);
        }else{
            randIndex = 0;
        }

        selectedLause = laused.remove(randIndex);

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



}
